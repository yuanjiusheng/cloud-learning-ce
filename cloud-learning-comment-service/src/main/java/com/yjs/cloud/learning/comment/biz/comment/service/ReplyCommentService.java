package com.yjs.cloud.learning.comment.biz.comment.service;

import com.yjs.cloud.learning.comment.biz.comment.bean.ReplyCommentCreateRequest;
import com.yjs.cloud.learning.comment.biz.comment.bean.ReplyCommentDeleteRequest;
import com.yjs.cloud.learning.comment.biz.comment.bean.ReplyCommentResponse;
import com.yjs.cloud.learning.comment.biz.comment.entity.ReplyComment;
import com.yjs.cloud.learning.comment.common.service.IBaseService;

import java.util.List;

/**
 * <p>
 * 评论回复服务
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
public interface ReplyCommentService extends IBaseService<ReplyComment> {

    /**
     * 评论回复
     * @param replyCommentCreateRequest 参数
     * @return 评论回复
     */
    ReplyCommentResponse create(ReplyCommentCreateRequest replyCommentCreateRequest);

    /**
     * 删除评论
     * @param replyCommentDeleteRequest 参数
     */
    void delete(ReplyCommentDeleteRequest replyCommentDeleteRequest);

    /**
     * 获取回复列表
     * @param commentIdList 评论id
     * @param memberId 会员id
     * @return 回复列表
     */
    List<ReplyCommentResponse> findList(List<Long> commentIdList, Long memberId);

}
