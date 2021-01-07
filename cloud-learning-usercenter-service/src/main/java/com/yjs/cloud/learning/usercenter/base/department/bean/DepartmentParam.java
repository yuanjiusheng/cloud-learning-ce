package com.yjs.cloud.learning.usercenter.base.department.bean;

import lombok.Data;

import java.util.List;

/**
 * 部门参数
 *
 * @author Bill.lai
 * @since 2020/6/18
 */
@Data
public class DepartmentParam {

    /**
     * 部门Id列表
     */
    private List<Long> departmentIdList;

    /**
     * 入职日期
     */
    private String joinDate;

    /**
     * 是否获取所有子节点（包括孙子节点...）
     */
    private Boolean fetchAll;

}
