package com.yjs.cloud.learning.usercenter.base.department.entity;

import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户与部门关系实体
 *
 * @author bill.lai
 * @since 2019/6/12 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDepartmentRelation extends BaseEntity {

    /**
     *  用户id
     */
    private long userId;

    /**
     *  部门id
     */
    private long departmentId;

}

