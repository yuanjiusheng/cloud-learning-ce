package com.yjs.cloud.learning.usercenter.base.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.usercenter.base.department.entity.Department;
import com.yjs.cloud.learning.usercenter.base.department.service.DepartmentService;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListRequest;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserUpdateRequest;
import com.yjs.cloud.learning.usercenter.base.user.entity.User;
import com.yjs.cloud.learning.usercenter.base.user.mapper.UserMapper;
import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import com.yjs.cloud.learning.usercenter.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.usercenter.common.util.StringUtils;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务实现
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final DepartmentService departmentService;

    /**
     * 获取用户
     * @param departmentId 部门id
     * @param userSearchKey 搜索关键字
     * @return 用户列表
     */
    @Override
    public List<User> list(Long departmentId, String userSearchKey){
        List<Long> departmentIdList = new ArrayList<>();
        if(departmentId != 0){
            departmentIdList.add(departmentId);
        }
        return list(departmentIdList, userSearchKey);
    }

    /**
     * 获取用户
     * @param departmentIdList 部门id列表
     * @param userSearchKey 搜索关键字
     * @return 用户列表
     */
    @Override
    public List<User> list(List<Long> departmentIdList, String userSearchKey){
        if(!StringUtils.isEmpty(userSearchKey)) {
            userSearchKey = "%" + userSearchKey + "%";
        }
        return userMapper.selectUser(departmentIdList, userSearchKey);
    }

    /**
     * 根据手机号获取用户信息
     * @param mobile 手机号码
     * @return 用户信息
     */
    @Override
    public UserResponse getByMobile(String mobile) {
        User user = getOne(new QueryWrapper<User>().lambda().eq(User::getMobile, mobile).or().eq(User::getUsername, mobile).or().eq(User::getEmail, mobile));
        if (user == null) {
            throw new GlobalException(String.format("user mobile[%s] is not found", mobile));
        }
        Long departmentId = 0L;
        if (!"admin".equals(user.getUsername())) {
            Department department = departmentService.getByUserId(user.getId());
            if (department == null) {
                throw new GlobalException(String.format("user mobile[%s] department is not found", mobile));
            }
            departmentId = department.getId();
        }
        UserResponse userResponse = user.convert();
        userResponse.setDepartmentId(departmentId);
        return userResponse;
    }

    /**
     * 获取用户列表
     * @param userGetListRequest 请求参数
     * @return 用户列表
     */
    @Override
    public UserGetListResponse findList(UserGetListRequest userGetListRequest) {
        if (userGetListRequest.getDepartmentId() == null) {
            userGetListRequest.setDepartmentId(0L);
        }
        Page<UserResponse> page = new Page<>(userGetListRequest.getCurrent(), userGetListRequest.getSize());
        page = userMapper.findList(page, userGetListRequest);
        UserGetListResponse userGetListResponse = new UserGetListResponse();
        userGetListResponse.setList(page.getRecords());
        userGetListResponse.setCurrent(page.getCurrent());
        userGetListResponse.setSize(page.getSize());
        userGetListResponse.setPages(page.getPages());
        userGetListResponse.setTotal(page.getTotal());
        return userGetListResponse;
    }

    @Override
    public List<UserResponse> getByIds(List<Long> ids) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(BaseEntity::getId, ids);
        List<User> userList = list(lambdaQueryWrapper);
        List<UserResponse> responseList = new ArrayList<>();
        for (User user : userList) {
            responseList.add(user.convert());
        }
        return responseList;
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest.getId() == null) {
            throw new GlobalException("ID为必填项");
        }
        User user = getById(userUpdateRequest.getId());
        if (user == null) {
            throw new GlobalException("找不到用户信息");
        }
        if (!StringUtils.isEmpty(userUpdateRequest.getName())) {
            user.setName(userUpdateRequest.getName());
        }
        if (!StringUtils.isEmpty(userUpdateRequest.getEmail())) {
            user.setEmail(userUpdateRequest.getEmail());
        }
        if (!StringUtils.isEmpty(userUpdateRequest.getMobile())) {
            user.setMobile(userUpdateRequest.getMobile());
        }
        if (!StringUtils.isEmpty(userUpdateRequest.getAvatar())) {
            user.setAvatar(userUpdateRequest.getAvatar());
        }
        if (!StringUtils.isEmpty(userUpdateRequest.getPassword())) {
            if(!user.getPassword().equals(userUpdateRequest.getOldPassword())) {
                throw new GlobalException("旧密码不正确");
            }
            user.setPassword(userUpdateRequest.getPassword());
        }
        updateById(user);
        return user.convert();
    }
}
