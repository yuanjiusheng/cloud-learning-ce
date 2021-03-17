package com.yjs.cloud.learning.behavior.biz.comment.service;

import com.yjs.cloud.learning.behavior.biz.comment.bean.*;
import com.yjs.cloud.learning.behavior.biz.comment.entity.Comment;
import com.yjs.cloud.learning.behavior.common.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 评论服务
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
public interface CommentService extends IBaseService<Comment> {

    /**
     * 发表评论
     * @param commentCreateRequest 参数
     * @return 评论
     */
    @Transactional(rollbackFor = Exception.class)
    CommentResponse create(CommentCreateRequest commentCreateRequest);

    /**
     * 删除类目
     * @param commentDeleteRequest 请求参数
     */
    @Transactional(rollbackFor = Exception.class)
    void delete(CommentDeleteRequest commentDeleteRequest);

    /**
     * 获取评论列表
     * @param commentListRequest 参数
     * @return 评论列表
     */
    CommentListResponse list(CommentListRequest commentListRequest);

    /**
     * 获取主题评论数量
     * @param commentCountListRequest 参数
     * @return 评论数量列表
     */
    List<CommentCountResponse> countList(CommentCountListRequest commentCountListRequest);
}
