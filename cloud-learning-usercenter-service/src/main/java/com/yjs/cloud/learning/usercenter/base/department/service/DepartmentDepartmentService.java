package com.yjs.cloud.learning.usercenter.base.department.service;


import com.yjs.cloud.learning.usercenter.base.department.entity.DepartmentRelation;
import com.yjs.cloud.learning.usercenter.common.service.IBaseService;

import java.util.List;

/**
 * 部门与部门关系Service
 *
 * @author bill.lai
 * @since 2019/6/19 17:58
 */
public interface DepartmentDepartmentService extends IBaseService<DepartmentRelation> {

    /**
     * 删除所有
     * @return 删除数量
     */
    int deleteAll();

    /**
     * 获取id的所有父级部门
     * @param childId 子id
     * @return 列表
     */
    List<DepartmentRelation> getByChildId(Long childId);

    /**
     * 根据部门id删除部门关系
     * @param departmentId 部门id
     * @return 删除数量
     */
    Integer deleteByChildId(Long departmentId);

    /**
     * 获取id的直接父部门
     * @param childId 子id
     * @return 部门信息
     */
    DepartmentRelation getSubByChildId(Long childId);
}
