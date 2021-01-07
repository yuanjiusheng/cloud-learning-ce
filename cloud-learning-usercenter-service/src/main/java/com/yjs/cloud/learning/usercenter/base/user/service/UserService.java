package com.yjs.cloud.learning.usercenter.base.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListRequest;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.base.user.entity.User;
import com.yjs.cloud.learning.usercenter.base.user.entity.UserVo;
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
     * 获取用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    User getByName(String name);

    /**
     * 根据工号获取用户
     *
     * @param code 工号
     * @return 用户信息
     */
    User getByCode(String code);

    /**
     * 根据钉钉id获取用户
     * @param ddId 钉钉id
     * @return 用户信息
     */
    User getByDdId(String ddId);

    /**
     * 删除所有
     *
     * @return 删除数量
     */
    int deleteAll();

    /**
     * 根据部门获取用户
     * @param departmentId  部门id
     * @param searchText 姓名搜索（模糊）
     * @return 用户列表
     */
    IPage<User> getByDepartmentId(Long departmentId, Page<User> page, String searchText);

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
     * @param userId 用户id
     * @return 用户信息
     */
    User detail(Long userId);

    /**
     * 获取用户信息
     * @param mobile 手机号码
     * @return 用户信息
     */
    UserResponse getByMobile(String mobile);

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户信息
     */
    User getByUsername(String username);

    /**
     * 获取用户信息（包含部门，岗位等）
     * @param departmentId 部门id
     * @param userId 用户id
     * @param searchFullName 用户名称搜索关键字
     * @param searchName 搜索登录名与id
     * @return 用户信息
     */
    List<UserVo> getUserVo(Long departmentId, Long userId, String searchFullName, String searchName);

    /**
     * 根据部门获取用户
     * @param departmentId  部门id
     * @param fetchAll fetchAll 是否获取所有子部门用户（包括孙子部门用户...）
     * @return 用户列表
     */
    List<User> getByDepartmentId(Long departmentId, Boolean fetchAll);

    /**
     * 根据部门，关键字（手机，工号，姓名）获取用户信息
     * @param departmentId 部门
     * @param searchText 关键字
     * @return 用户列表
     */
    List<User> getUserByDepartmentIdAndKeyword(Long departmentId, String searchText);

    /**
     * 根据部门获取用户
     * @param departmentIdList  部门id
     * @param fetchAll fetchAll 是否获取所有子部门用户（包括孙子部门用户...）
     * @return 用户列表
     */
    List<User> getByDepartmentIdList(List<Long> departmentIdList, Boolean fetchAll);

    /**
     * 获取用户列表
     * @param userGetListRequest 请求参数
     * @return 用户列表
     */
    UserGetListResponse findList(UserGetListRequest userGetListRequest);
}
