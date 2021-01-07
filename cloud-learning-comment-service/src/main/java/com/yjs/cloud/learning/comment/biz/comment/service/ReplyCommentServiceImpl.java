package com.yjs.cloud.learning.comment.biz.comment.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.comment.biz.api.member.MemberApi;
import com.yjs.cloud.learning.comment.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.comment.biz.comment.bean.ReplyCommentCreateRequest;
import com.yjs.cloud.learning.comment.biz.comment.bean.ReplyCommentDeleteRequest;
import com.yjs.cloud.learning.comment.biz.comment.bean.ReplyCommentResponse;
import com.yjs.cloud.learning.comment.biz.like.bean.LikeCountListRequest;
import com.yjs.cloud.learning.comment.biz.like.bean.LikeCountResponse;
import com.yjs.cloud.learning.comment.biz.like.bean.LikeGetListRequest;
import com.yjs.cloud.learning.comment.biz.like.bean.LikeResponse;
import com.yjs.cloud.learning.comment.biz.like.enums.LikeTopicType;
import com.yjs.cloud.learning.comment.biz.like.service.LikeService;
import com.yjs.cloud.learning.comment.biz.comment.entity.ReplyComment;
import com.yjs.cloud.learning.comment.biz.comment.mapper.ReplyCommentMapper;
import com.yjs.cloud.learning.comment.common.entity.BaseEntity;
import com.yjs.cloud.learning.comment.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.comment.common.util.StringUtils;
import com.yjs.cloud.learning.comment.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论回复服务实现
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@AllArgsConstructor
@Service
public class ReplyCommentServiceImpl extends BaseServiceImpl<ReplyCommentMapper, ReplyComment> implements ReplyCommentService {

    private final MemberApi memberApi;
    private final LikeService likeService;

    /**
     * 评论回复
     * @param replyCommentCreateRequest 参数
     * @return 评论回复
     */
    @Override
    public ReplyCommentResponse create(ReplyCommentCreateRequest replyCommentCreateRequest) {
        if (replyCommentCreateRequest.getMemberId() == null) {
            throw new GlobalException("请先登录");
        }
        if (StringUtils.isEmpty(replyCommentCreateRequest.getContent())) {
            throw new GlobalException("评论内容不能为空");
        }
        if (replyCommentCreateRequest.getReplyCommentId() == null) {
            throw new GlobalException("回复的评论ID为必填项");
        }
        if (replyCommentCreateRequest.getToMemberId() == null) {
            throw new GlobalException("回复的评论的用户ID为必填项");
        }
        ReplyComment replyComment = replyCommentCreateRequest.convert();
        save(replyComment);
        return replyComment.convert();
    }

    /**
     * 删除评论
     * @param replyCommentDeleteRequest 参数
     */
    @Override
    public void delete(ReplyCommentDeleteRequest replyCommentDeleteRequest) {
        if (replyCommentDeleteRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        ReplyComment replyComment = getById(replyCommentDeleteRequest.getId());
        if (replyComment == null) {
            throw new GlobalException("评论不存在");
        }
        removeById(replyCommentDeleteRequest.getId());
    }

    /**
     * 获取回复列表
     * @param commentIdList 评论id
     * @param memberId 会员id
     * @return 回复列表
     */
    @Override
    public List<ReplyCommentResponse> findList(List<Long> commentIdList, Long memberId) {
        LambdaQueryWrapper<ReplyComment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(ReplyComment::getCommentId, commentIdList);
        lambdaQueryWrapper.orderByAsc(BaseEntity::getCreateTime);
        List<ReplyComment> replyCommentList = list(lambdaQueryWrapper);
        List<ReplyCommentResponse> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(replyCommentList)) {
            List<Long> memberIdList = new ArrayList<>();
            List<Long> replyIdList = new ArrayList<>();
            for (ReplyComment replyComment : replyCommentList) {
                if (!memberIdList.contains(replyComment.getMemberId())) {
                    memberIdList.add(replyComment.getMemberId());
                }
                if (!memberIdList.contains(replyComment.getToMemberId())) {
                    memberIdList.add(replyComment.getToMemberId());
                }
                replyIdList.add(replyComment.getId());
            }
            // 获取会员信息
            List<MemberResponse> memberList = memberApi.getByIds(memberIdList);
            Map<Long, MemberResponse> memberMap = new HashMap<>(16);
            for (MemberResponse memberResponse : memberList) {
                memberMap.put(memberResponse.getId(), memberResponse);
            }
            Map<Long, LikeResponse> likeMap = new HashMap<>(16);
            Map<Long, Long> countLikeMap = new HashMap<>(16);
            if (memberId != null) {
                // 获取会员点赞信息
                LikeGetListRequest likeGetListRequest = new LikeGetListRequest();
                likeGetListRequest.setMemberId(memberId);
                likeGetListRequest.setTopicIdList(replyIdList);
                likeGetListRequest.setTopicType(LikeTopicType.reply_comment);
                List<LikeResponse> list = likeService.getList(likeGetListRequest);
                for (LikeResponse likeResponse : list) {
                    likeMap.put(likeResponse.getTopicId(), likeResponse);
                }
                // 评论点赞总数
                LikeCountListRequest likeCountListRequest = new LikeCountListRequest();
                likeCountListRequest.setTopicType(LikeTopicType.reply_comment);
                likeCountListRequest.setTopicIdList(replyIdList);
                List<LikeCountResponse> countList = likeService.countList(likeCountListRequest);
                for (LikeCountResponse likeCountResponse : countList) {
                    countLikeMap.put(likeCountResponse.getTopicId(), likeCountResponse.getNum());
                }
            }
            for (ReplyComment replyComment : replyCommentList) {
                ReplyCommentResponse replyCommentResponse = replyComment.convert();
                replyCommentResponse.setMember(memberMap.get(replyComment.getMemberId()));
                replyCommentResponse.setToMember(memberMap.get(replyComment.getToMemberId()));
                replyCommentResponse.setLike(likeMap.get(replyComment.getId()));
                Long count = countLikeMap.get(replyComment.getId());
                replyCommentResponse.setLikeCount(count == null ? 0 : count);
                result.add(replyCommentResponse);
            }
        }
        return result;
    }

}
