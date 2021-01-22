package com.yjs.cloud.learning.usercenter.base.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListRequest;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.base.user.entity.User;
import com.yjs.cloud.learning.usercenter.base.user.mapper.UserMapper;
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
        return user.convert();
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
}
