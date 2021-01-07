package com.yjs.cloud.learning.usercenter.base.department.service;

import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListRequest;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListResponse;
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
     * 获取部门列表
     * @return 部门列表
     */
    List<Department> listOrderByCreateTime();

    /**
     * 根据钉钉部门id获取部门信息
     * @param dingTalkDeptId 钉钉部门id
     * @return 部门信息
     */
    Department getByDingTalkId(Long dingTalkDeptId);

    /**
     * 根据名称获取部门
     * @param name 部门名称
     * @return 部门信息
     */
    Department getByName(String name);

    /**
     * 根据名称列表获取部门信息
     * @param nameList 部门名称列表
     * @return 部门信息列表
     */
    List<Department> getByNameList(List<String> nameList);

    /**
     * 删除所有
     * @return 删除数量
     */
    int deleteAll();

    /**
     * 获取子部门
     * @param id  部门id
     * @param fetchAll 是否获取所有子节点（包括孙子节点...）
     * @return 子部门列表
     */
    List<Department> list(Long id, Boolean fetchAll);

    /**
     * 获取用户直属部门的子部门
     * @param userDepartmentId 用户直属部门id
     * @param id 部门id
     * @return 部门列表
     */
    List<Department> getByUserDepartmentIdAndId(Long userDepartmentId, Long id);

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
    @Override
    List<Department> list();

    /**
     * 获取用户组的用户人数
     * @param departmentList 用户组列表
     * @param date 日期 "yyyy-MM-dd"
     * @return 用户列表
     */
    List<Department> getUserNumber(List<Long> departmentList, String date);

    List<Department> list(List<Long> idList, Boolean fetchAll);
}
