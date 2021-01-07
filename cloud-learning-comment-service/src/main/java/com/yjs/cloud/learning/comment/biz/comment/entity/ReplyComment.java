package com.yjs.cloud.learning.comment.biz.comment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.comment.biz.comment.bean.ReplyCommentResponse;
import com.yjs.cloud.learning.comment.common.entity.BaseEntity;
import com.yjs.cloud.learning.comment.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 回复评论
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_reply_comment")
public class ReplyComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    private Long commentId;

    /**
     * 回复评论id，也就是父ID，回复评论表的评论是，值跟评论id相等
     */
    private Long replyCommentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 当前评论的用户id
     */
    private Long memberId;

    /**
     * 回复的评论的用户id
     */
    private Long toMemberId;

    public ReplyCommentResponse convert() {
        ReplyCommentResponse bean = new ReplyCommentResponse();
        BeanCopierUtils.copy(this, bean);
        return bean;
    }

}
