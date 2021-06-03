package com.yjs.cloud.learning.usercenter.base.department.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetByIdsRequest;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListRequest;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListResponse;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentResponse;
import com.yjs.cloud.learning.usercenter.base.department.entity.Department;
import com.yjs.cloud.learning.usercenter.base.department.mapper.DepartmentMapper;
import com.yjs.cloud.learning.usercenter.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public DepartmentGetListResponse findList(DepartmentGetListRequest departmentGetListRequest) {
        List<DepartmentResponse> list = departmentMapper.findList(departmentGetListRequest);
        DepartmentGetListResponse departmentGetListResponse = new DepartmentGetListResponse();
        departmentGetListResponse.setList(list);
        return departmentGetListResponse;
    }

    @Override
    public Department getByUserId(Long userId){
        return departmentMapper.selectByUserId(userId);
    }

    @Override
    public List<Department> getList(){
        return departmentMapper.selectAll();
    }

    @Override
    public List<Department> getList(List<Long> idList, Boolean fetchAll) {
        return departmentMapper.getList(idList, fetchAll);
    }

    @Override
    public List<DepartmentResponse> getByIds(DepartmentGetByIdsRequest departmentGetByIdsRequest) {
        List<DepartmentResponse> responseList = new ArrayList<>();
        if (CollectionUtils.isEmpty(departmentGetByIdsRequest.getIds())) {
            return responseList;
        }
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Department::getId, departmentGetByIdsRequest.getIds());
        List<Department> list = list(lambdaQueryWrapper);
        for (Department department : list) {
            responseList.add(department.convert());
        }
        return responseList;
    }
}
