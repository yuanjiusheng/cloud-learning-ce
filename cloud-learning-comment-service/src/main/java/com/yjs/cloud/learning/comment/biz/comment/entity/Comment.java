package com.yjs.cloud.learning.comment.biz.comment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.comment.biz.comment.bean.CommentResponse;
import com.yjs.cloud.learning.comment.biz.comment.enums.CommentTopicType;
import com.yjs.cloud.learning.comment.common.entity.BaseEntity;
import com.yjs.cloud.learning.comment.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_comment")
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主题ID
     */
    private Long topicId;

    /**
     * 主题类型
     */
    private CommentTopicType topicType;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论用户id
     */
    private Long memberId;

    public CommentResponse convert() {
        CommentResponse bean = new CommentResponse();
        BeanCopierUtils.copy(this, bean);
        return bean;
    }
}
