package com.yjs.cloud.learning.usercenter.base.department.service;


import com.yjs.cloud.learning.usercenter.base.department.entity.DepartmentRelation;
import com.yjs.cloud.learning.usercenter.base.department.mapper.DepartmentRelationMapper;
import com.yjs.cloud.learning.usercenter.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门与部门服务实现
 *
 * @author Andrew.xiao
 * @since 2019/6/26
 */
@AllArgsConstructor
@Service
public class DepartmentDepartmentServiceImpl extends BaseServiceImpl<DepartmentRelationMapper, DepartmentRelation> implements DepartmentDepartmentService {

    private final DepartmentRelationMapper departmentDepartmentMapper;

    /**
     * 删除所有
     * @return 删除数量
     */
    @Override
    public int deleteAll(){
        return departmentDepartmentMapper.deleteAll();
    }

    /**
     * 获取id的所有父级部门
     * @param childId 子id
     * @return 列表
     */
    @Override
    public List<DepartmentRelation> getByChildId(Long childId){
        return departmentDepartmentMapper.getByChildId(childId);
    }

    /**
     * 根据部门id删除部门关系
     * @param departmentId 部门id
     * @return 删除数量
     */
    @Override
    public Integer deleteByChildId(Long departmentId){
        return departmentDepartmentMapper.deleteByChildId(departmentId);
    }

    /**
     * 获取id的直接父部门
     * @param childId 子id
     * @return 部门信息
     */
    @Override
    public DepartmentRelation getSubByChildId(Long childId){
        return departmentDepartmentMapper.getSubByChildId(childId);
    }

}
