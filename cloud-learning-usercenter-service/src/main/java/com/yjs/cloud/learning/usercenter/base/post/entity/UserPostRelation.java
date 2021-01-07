package com.yjs.cloud.learning.usercenter.base.post.entity;

import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户与岗位关系实体
 *
 * @author bill.lai
 * @since 2019/6/12 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPostRelation extends BaseEntity {

    /**
     *  用户id
     */
    private long userId;

    /**
     *  岗位id
     */
    private long postId;

}

