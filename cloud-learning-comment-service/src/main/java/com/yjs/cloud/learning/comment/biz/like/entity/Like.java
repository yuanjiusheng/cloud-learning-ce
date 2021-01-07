package com.yjs.cloud.learning.comment.biz.like.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.comment.biz.like.bean.LikeResponse;
import com.yjs.cloud.learning.comment.biz.like.enums.LikeTopicType;
import com.yjs.cloud.learning.comment.common.entity.BaseEntity;
import com.yjs.cloud.learning.comment.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 点赞
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_like")
public class Like extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主题ID
     */
    private Long topicId;

    /**
     * 主题类型
     */
    private LikeTopicType topicType;

    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 点赞状态 0=取消赞，1=有效赞
     */
    private Boolean status;

    public LikeResponse convert() {
        LikeResponse bean = new LikeResponse();
        BeanCopierUtils.copy(this, bean);
        return bean;
    }
}
