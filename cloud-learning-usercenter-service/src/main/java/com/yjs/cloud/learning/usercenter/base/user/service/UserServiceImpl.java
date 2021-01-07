package com.yjs.cloud.learning.usercenter.base.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListRequest;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.base.user.entity.User;
import com.yjs.cloud.learning.usercenter.base.user.entity.UserVo;
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
     * 获取用户信息
     * @param name 用户名
     * @return 用户信息
     */
    @Override
    public User getByName(String name) {
        return userMapper.selectByName(name);
    }

    /**
     * 根据工号获取用户
     * @param code 工号
     * @return 用户信息
     */
    @Override
    public User getByCode(String code){
        return userMapper.selectByCode(code);
    }

    /**
     * 根据钉钉id获取用户
     * @param ddId 钉钉id
     * @return 用户信息
     */
    @Override
    public User getByDdId(String ddId){
        return userMapper.selectByDdId(ddId);
    }

    /**
     * 删除所有
     * @return 删除数量
     */
    @Override
    public int deleteAll(){
        return userMapper.deleteAll();
    }

    /**
     * 根据部门获取用户
     * @param departmentId  部门id
     * @param searchText 姓名搜索（模糊）
     * @return 用户列表
     */
    @Override
    public IPage<User> getByDepartmentId(Long departmentId, Page<User> page, String searchText) {
        if (!StringUtils.isEmpty(searchText)) {
            searchText = "%" + searchText + "%";
        }
        return userMapper.selectByDepartmentId(page, departmentId, searchText);
    }

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
     * 获取用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public User detail(Long userId) {
        return userMapper.selectUserById(userId);
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
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public User getByUsername(String username) {
        return super.getOne(new QueryWrapper<User>().lambda().eq(User::getName, username),false);
    }

    /**
     * 获取用户信息（包含部门，岗位等）
     * @param departmentId 部门id
     * @param userId 用户id
     * @param searchFullName 用户名称搜索关键字
     * @param searchName 搜索登录名与id
     * @return 用户信息
     */
    @Override
    public List<UserVo> getUserVo(Long departmentId, Long userId, String searchFullName, String searchName){
        return userMapper.selectUserVo(departmentId, searchFullName, userId, searchName);
    }

    @Override
    public List<User> getUserByDepartmentIdAndKeyword(Long departmentId, String searchText) {
        return userMapper.getUserByDepartmentIdAndKeyword(departmentId, searchText);
    }

    /**
     * 根据部门获取用户
     * @param departmentId  部门id
     * @param fetchAll fetchAll 是否获取所有子部门用户（包括孙子部门用户...）
     * @return 用户列表
     */
    @Override
    public List<User> getByDepartmentId(Long departmentId, Boolean fetchAll) {
        return null;
    }

    /**
     * 根据部门获取用户
     * @param departmentIdList  部门id
     * @param fetchAll fetchAll 是否获取所有子部门用户（包括孙子部门用户...）
     * @return 用户列表
     */
    @Override
    public List<User> getByDepartmentIdList(List<Long> departmentIdList, Boolean fetchAll) {
        return userMapper.findUsers(departmentIdList, fetchAll);
    }

    /**
     * 获取用户列表
     * @param userGetListRequest 请求参数
     * @return 用户列表
     */
    @Override
    public UserGetListResponse findList(UserGetListRequest userGetListRequest) {
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
