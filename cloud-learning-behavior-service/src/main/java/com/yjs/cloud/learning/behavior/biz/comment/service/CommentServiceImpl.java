package com.yjs.cloud.learning.behavior.biz.comment.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.behavior.biz.api.learn.LessonApi;
import com.yjs.cloud.learning.behavior.biz.api.member.MemberApi;
import com.yjs.cloud.learning.behavior.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.behavior.biz.comment.bean.*;
import com.yjs.cloud.learning.behavior.biz.comment.enums.CommentTopicType;
import com.yjs.cloud.learning.behavior.biz.like.bean.LikeCountListRequest;
import com.yjs.cloud.learning.behavior.biz.like.bean.LikeCountResponse;
import com.yjs.cloud.learning.behavior.biz.like.bean.LikeGetListRequest;
import com.yjs.cloud.learning.behavior.biz.like.bean.LikeResponse;
import com.yjs.cloud.learning.behavior.biz.like.enums.LikeTopicType;
import com.yjs.cloud.learning.behavior.biz.like.service.LikeService;
import com.yjs.cloud.learning.behavior.biz.comment.entity.Comment;
import com.yjs.cloud.learning.behavior.biz.comment.mapper.CommentMapper;
import com.yjs.cloud.learning.behavior.biz.sensitiveword.service.WordService;
import com.yjs.cloud.learning.behavior.common.entity.BaseEntity;
import com.yjs.cloud.learning.behavior.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.behavior.common.util.StringUtils;
import com.yjs.cloud.learning.behavior.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论服务实现
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@AllArgsConstructor
@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentMapper, Comment> implements CommentService {

    private final ReplyCommentService replyCommentService;
    private final MemberApi memberApi;
    private final LessonApi lessonApi;
    private final LikeService likeService;
    private final CommentMapper commentMapper;
    private final WordService wordService;

    /**
     * 发表评论
     * @param commentCreateRequest 参数
     * @return 评论
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommentResponse create(CommentCreateRequest commentCreateRequest) {
        if (commentCreateRequest.getMemberId() == null) {
            throw new GlobalException("请先登录");
        }
        if (StringUtils.isEmpty(commentCreateRequest.getContent())) {
            throw new GlobalException("评论内容不能为空");
        }
        if (commentCreateRequest.getTopicId() == null) {
            throw new GlobalException("评论主题ID为必填项");
        }
        if (commentCreateRequest.getTopicType() == null) {
            throw new GlobalException("评论主题类型为必填项");
        }
        Comment comment = commentCreateRequest.convert();
        comment.setContent(wordService.replace(comment.getContent()));
        save(comment);
        return comment.convert();
    }

    /**
     * 删除类目
     * @param commentDeleteRequest 请求参数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(CommentDeleteRequest commentDeleteRequest) {
        if (commentDeleteRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        Comment comment = getById(commentDeleteRequest.getId());
        if (comment == null) {
            throw new GlobalException("评论不存在");
        }
        removeById(commentDeleteRequest.getId());
    }

    private CommentTopicResponse getTopic(Long topicId, CommentTopicType topicType) {
        CommentTopicResponse result;
        switch (topicType) {
            case lesson:
                result = lessonApi.getById(topicId);
                break;
            default:
                result = null;
        }
        return result;
    }

    /**
     * 获取评论列表
     * @param commentListRequest 参数
     * @return 评论列表
     */
    @Override
    public CommentListResponse list(CommentListRequest commentListRequest) {
        Page<Comment> page = new Page<>(commentListRequest.getCurrent(), commentListRequest.getSize());
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(commentListRequest.getTopicId() != null) {
            lambdaQueryWrapper.eq(Comment::getTopicId, commentListRequest.getTopicId());
        }
        if(commentListRequest.getTopicType() != null) {
            lambdaQueryWrapper.eq(Comment::getTopicType, commentListRequest.getTopicType());
        }
        if(commentListRequest.getMemberId() != null) {
            lambdaQueryWrapper.eq(Comment::getMemberId, commentListRequest.getMemberId());
        }
        lambdaQueryWrapper.orderByDesc(BaseEntity::getCreateTime);
        page = baseMapper.selectPage(page, lambdaQueryWrapper);
        List<CommentResponse> records = new ArrayList<>();
        if (!CollectionUtils.isEmpty(page.getRecords())) {
            List<Long> memberIdList = new ArrayList<>();
            List<Long> commentIdList = new ArrayList<>();
            // 主题内容
            Map<String, CommentTopicResponse> topicMap = new HashMap<>(16);
            for (Comment comment : page.getRecords()) {
                if (!memberIdList.contains(comment.getMemberId())) {
                    memberIdList.add(comment.getMemberId());
                }
                commentIdList.add(comment.getId());
                String key = comment.getTopicType() + "_" + comment.getTopicId();
                if (topicMap.get(key) == null) {
                    topicMap.put(key, getTopic(comment.getTopicId(), comment.getTopicType()));
                }
            }
            // 获取评论人信息
            Map<Long, MemberResponse> memberMap = memberApi.getMemberMap(memberIdList);
            // 获取回复评论
            List<ReplyCommentResponse> replyCommentList = replyCommentService.findList(commentIdList, commentListRequest.getMemberId());
            Map<Long, List<ReplyCommentResponse>> replyCommentMap = new HashMap<>(16);
            if (!CollectionUtils.isEmpty(replyCommentList)) {
                for (ReplyCommentResponse replyCommentResponse : replyCommentList) {
                    List<ReplyCommentResponse> list = replyCommentMap.computeIfAbsent(replyCommentResponse.getCommentId(), k -> new ArrayList<>());
                    list.add(replyCommentResponse);
                }
            }
            Map<Long, LikeResponse> likeMap = new HashMap<>(16);
            Map<Long, Long> countLikeMap = new HashMap<>(16);
            if (commentListRequest.getMemberId() != null) {
                // 获取会员是否点赞了
                LikeGetListRequest likeGetListRequest = new LikeGetListRequest();
                likeGetListRequest.setMemberId(commentListRequest.getMemberId());
                likeGetListRequest.setTopicIdList(commentIdList);
                likeGetListRequest.setTopicType(LikeTopicType.comment);
                List<LikeResponse> list = likeService.getList(likeGetListRequest);
                for (LikeResponse likeResponse : list) {
                    likeMap.put(likeResponse.getTopicId(), likeResponse);
                }
            }
            // 评论点赞总数
            LikeCountListRequest likeCountListRequest = new LikeCountListRequest();
            likeCountListRequest.setTopicIdList(commentIdList);
            likeCountListRequest.setTopicType(LikeTopicType.comment);
            List<LikeCountResponse> countList = likeService.countList(likeCountListRequest);
            for (LikeCountResponse likeCountResponse : countList) {
                countLikeMap.put(likeCountResponse.getTopicId(), likeCountResponse.getNum());
            }
            for (Comment comment : page.getRecords()) {
                CommentResponse commentResponse = comment.convert();
                commentResponse.setMember(memberMap.get(comment.getMemberId()));
                commentResponse.setReplyList(replyCommentMap.get(comment.getId()));
                commentResponse.setLike(likeMap.get(comment.getId()));
                Long count = countLikeMap.get(comment.getId());
                commentResponse.setLikeCount(count == null ? 0 : count);
                commentResponse.setTopic(topicMap.get(comment.getTopicType() + "_" + comment.getTopicId()));
                records.add(commentResponse);
            }
        }
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setList(records);
        commentListResponse.setPages(page.getPages());
        commentListResponse.setCurrent(page.getCurrent());
        commentListResponse.setSize(page.getSize());
        commentListResponse.setTotal(page.getTotal());
        return commentListResponse;
    }

    /**
     * 获取主题评论数量
     * @param commentCountListRequest 参数
     * @return 评论数量列表
     */
    @Override
    public List<CommentCountResponse> countList(CommentCountListRequest commentCountListRequest) {
        return commentMapper.countList(commentCountListRequest);
    }

}
