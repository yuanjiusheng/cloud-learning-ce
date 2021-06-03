package com.yjs.cloud.learning.usercenter.base.department.service;

import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetByIdsRequest;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListRequest;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListResponse;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentResponse;
import com.yjs.cloud.learning.usercenter.base.department.entity.Department;
import com.yjs.cloud.learning.usercenter.common.service.IBaseService;

import java.util.List;

/**
 * 部门服务
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
public interface DepartmentService extends IBaseService<Department> {

    /**
     * 获取部门列表
     * @param departmentGetListRequest 参数
     * @return 部门列表
     */
    DepartmentGetListResponse findList(DepartmentGetListRequest departmentGetListRequest);

    /**
     * 获取用户的部门
     * @param userId 用户id
     * @return 部门
     */
    Department getByUserId(Long userId);

    /**
     * 获取所有用户组
     * @return 用户组列表
     */
    List<Department> getList();

    /**
     * 获取子部门
     * @param idList  部门id
     * @param fetchAll 是否获取所有子节点（包括孙子节点...）
     * @return 子部门列表
     */
    List<Department> getList(List<Long> idList, Boolean fetchAll);

    /**
     * 根据id获取部门
     * @param departmentGetByIdsRequest 参数
     * @return 部门列表
     */
    List<DepartmentResponse> getByIds(DepartmentGetByIdsRequest departmentGetByIdsRequest);
}
