package com.yjs.cloud.learning.usercenter.base.department.entity;

import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门与部门关系表
 *
 * @author bill.lai
 * @since 2019/6/12 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentRelation extends BaseEntity {

    /**
     *  子部门id
     */
    private Long childId;

    /**
     *  上级部门id
     */
    private Long fatherId;

    /**
     *  直属上级部门id
     */
    private Long directFatherId;

    /**
     *  是否是子部门
     */
    private Boolean isSub;

}

