package com.yjs.cloud.learning.usercenter.base.department.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListRequest;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListResponse;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentResponse;
import com.yjs.cloud.learning.usercenter.base.department.entity.Department;
import com.yjs.cloud.learning.usercenter.base.department.mapper.DepartmentMapper;
import com.yjs.cloud.learning.usercenter.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 部门服务实现
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    /**
     * 获取部门列表
     * @param departmentGetListRequest 参数
     * @return 部门列表
     */
    @Override
    public DepartmentGetListResponse findList(DepartmentGetListRequest departmentGetListRequest) {
        List<DepartmentResponse> list = departmentMapper.findList(departmentGetListRequest);
        DepartmentGetListResponse departmentGetListResponse = new DepartmentGetListResponse();
        departmentGetListResponse.setList(list);
        return departmentGetListResponse;
    }

    /**
     * 获取部门列表
     * @return 部门列表
     */
    @Override
    public List<Department> listOrderByCreateTime(){
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return list(queryWrapper);
    }

    /**
     * 根据钉钉部门id获取部门信息
     * @param dingTalkDeptId 钉钉部门id
     * @return 部门信息
     */
    @Override
    public Department getByDingTalkId(Long dingTalkDeptId){
        return departmentMapper.selectByDingTalkId(dingTalkDeptId);
    }

    /**
     * 删除所有
     * @return 删除数量
     */
    @Override
    public int deleteAll(){
        return departmentMapper.deleteAll();
    }

    @Override
    public List<Department> list(Long id, Boolean fetchAll) {
        return null;
    }

    /**
     * 根据名称获取部门
     * @param name 部门名称
     * @return 部门信息
     */
    @Override
    public Department getByName(String name){
        return departmentMapper.selectByName(name);
    }

    /**
     * 根据名称列表获取部门信息
     * @param nameList 部门名称列表
     * @return 部门信息列表
     */
    @Override
    public List<Department> getByNameList(List<String> nameList){
        return departmentMapper.selectByNameList(nameList);
    }

    /**
     * 获取用户直属部门的子部门@param id部门
     * @param userDepartmentId 用户直属部门id
     * @param id 部门id
     * @return 部门列表
     */
    @Override
    public List<Department> getByUserDepartmentIdAndId(Long userDepartmentId, Long id){
        return departmentMapper.selectByUserDepartmentIdAndId(userDepartmentId, id);
    }

    /**
     * 获取用户的部门
     * @param userId 用户id
     * @return 部门
     */
    @Override
    public Department getByUserId(Long userId){
        return departmentMapper.selectByUserId(userId);
    }

    /**
     * 获取所有用户组
     * @return 用户组列表
     */
    @Override
    public List<Department> list(){
        return departmentMapper.selectAll();
    }

    /**
     * 获取用户组的用户人数
     * @param departmentIdList 用户组列表
     * @param dateStr 日期 "yyyy-MM-dd"
     * @return 用户列表
     */
    @Override
    public List<Department> getUserNumber(List<Long> departmentIdList, String dateStr) {
        departmentIdList = new ArrayList<>();
        departmentIdList.add(0L);
        return departmentMapper.selectList(departmentIdList, true);
    }

    /**
     * 获取子部门
     * @param idList  部门id
     * @param fetchAll 是否获取所有子节点（包括孙子节点...）
     * @return 子部门列表
     */
    @Override
    public List<Department> list(List<Long> idList, Boolean fetchAll) {
        return departmentMapper.selectList(idList, fetchAll);
    }

}
