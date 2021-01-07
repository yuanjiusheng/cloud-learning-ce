package com.yjs.cloud.learning.auth.biz.role.service;


import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import com.yjs.cloud.learning.auth.common.service.IBaseService;

import java.util.List;

/**
 * 角色服务
 *
 * @author Andrew.xiao
 * @since 2019/6/12
 */
public interface RoleService extends IBaseService<Role> {
    /**
     * 获取某角色下的权限
     * @param id 角色id
     * @return 角色下的权限列表
     */
    List<Authority> getRolePermissions(Long id);

    /**
     * 更新角色下的权限
     * @param id 角色id
     * @param authorities 权限集合
     */
    void updateRolePermissions(Long id, List<Authority> authorities);

    /**
     * 获取用户下的角色
     * @param userId 用户id
     * @return 用户角色列表
     */
    List<Role> getByUserId(Long userId);

    /**
     * 插入用户角色
     * @param id 用户id
     * @param role 角色信息
     */
    void insertUserRole(Long id, Role role);

    /**
     * 删除用户角色
     * @param id 用户id
     */
    void deleteUserRoles(Long id);

    /**
     * 获取用户下的角色
     * @param id 用户id
     * @return 用户角色列表
     */
    List<Role> getUserRoles(Long id);

    /**
     * 更新用户下的角色
     * @param id 用户id
     * @param roles 角色列表
     */
    void updateUserRoles(Long id, List<Role> roles);
}
