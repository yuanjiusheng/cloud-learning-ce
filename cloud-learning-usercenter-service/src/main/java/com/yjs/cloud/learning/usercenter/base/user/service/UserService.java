package com.yjs.cloud.learning.usercenter.base.user.service;

import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListRequest;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserUpdateRequest;
import com.yjs.cloud.learning.usercenter.base.user.entity.User;
import com.yjs.cloud.learning.usercenter.common.service.IBaseService;

import java.util.List;


/**
 * 用户服务
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
public interface UserService extends IBaseService<User> {

    /**
     * 获取用户列表
     * @param departmentId 部门id
     * @param userSearchKey 搜索关键字
     * @return 用户列表
     */
    List<User> list(Long departmentId, String userSearchKey);

    /**
     * 获取用户列表
     * @param departmentIdList 部门id列表
     * @param userSearchKey 搜索关键字
     * @return 用户列表
     */
    List<User> list(List<Long> departmentIdList, String userSearchKey);

    /**
     * 获取用户信息
     * @param mobile 手机号码
     * @return 用户信息
     */
    UserResponse getByMobile(String mobile);

    /**
     * 获取用户列表
     * @param userGetListRequest 请求参数
     * @return 用户列表
     */
    UserGetListResponse findList(UserGetListRequest userGetListRequest);

    /**
     * 修改用户信息
     * @param userUpdateRequest 参数
     * @return 用户信息
     */
    UserResponse updateUser(UserUpdateRequest userUpdateRequest);

    /**
     * 根据id列表获取用户信息
     * @param ids id列表
     * @return 用户信息
     */
    List<UserResponse> getByIds(List<Long> ids);

}
