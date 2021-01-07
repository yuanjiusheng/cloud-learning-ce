package com.yjs.cloud.learning.usercenter.base.department.entity;

import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门实体
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Department extends BaseEntity {

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 启用|禁用
     */
    private Boolean enabled;

}
