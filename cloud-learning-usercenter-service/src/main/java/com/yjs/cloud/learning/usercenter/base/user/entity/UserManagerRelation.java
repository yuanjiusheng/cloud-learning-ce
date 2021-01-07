package com.yjs.cloud.learning.usercenter.base.user.entity;

import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户与上级领导关系实体
 *
 * @author bill.lai
 * @since 2019/6/12 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserManagerRelation extends BaseEntity {

    /**
     *  用户id
     */
    private long userId;

    /**
     *  上级领导id
     */
    private long managerId;

}
