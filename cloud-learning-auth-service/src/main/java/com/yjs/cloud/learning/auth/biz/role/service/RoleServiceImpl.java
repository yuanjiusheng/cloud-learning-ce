package com.yjs.cloud.learning.auth.biz.role.service;

import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.authority.service.AuthorityService;
import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import com.yjs.cloud.learning.auth.biz.role.mapper.RoleMapper;
import com.yjs.cloud.learning.auth.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色服务实现
 *
 * @author Andrew.xiao
 * @since 2019/6/12
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

    private final AuthorityService authorityService;

    private final RoleMapper roleMapper;

    /**
     * 获取某角色下的权限
     * @param id 角色id
     * @return 角色下的权限列表
     */
    @Override
    public List<Authority> getRolePermissions(Long id) {
        return authorityService.getByRoleId(id);
    }

    /**
     * 更新角色下的权限
     * @param id 角色id
     * @param authorities 权限集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRolePermissions(Long id, List<Authority> authorities) {
        deleteRolePermissions(id);
        insertRolePermissions(id, authorities);
    }

    /**
     * 获取用户下的角色
     * @param userId 用户id
     * @return 用户角色列表
     */
    @Override
    public List<Role> getByUserId(Long userId) {
        return roleMapper.selectByUserId(userId);
    }

    /**
     * 为角色添加权限
     * @param id 角色id
     * @param authorities 权限集合
     */
    private void insertRolePermissions(Long id, List<Authority> authorities) {
        for (Authority authority : authorities) {
            roleMapper.insertRolePermissions(id, authority);
        }
    }

    /**
     * 删除某角色下的权限
     * @param id 角色id
     */
    private void deleteRolePermissions(Long id) {
        roleMapper.deleteRolePermissions(id);
    }

    /**
     * 插入用户角色
     * @param id 用户id
     * @param role 角色信息
     */
    @Override
    public void insertUserRole(Long id, Role role){
        roleMapper.insertUserRole(id, role);
    }

    /**
     * 删除用户角色
     * @param id 用户id
     */
    @Override
    public void deleteUserRoles(Long id){
        roleMapper.deleteUserRoles(id);
    }

    /**
     * 获取用户下的角色
     * @param id 用户id
     * @return 用户角色列表
     */
    @Override
    public List<Role> getUserRoles(Long id) {
        return getByUserId(id);
    }

    /**
     * 更新用户下的角色
     * @param id 用户id
     * @param roles 角色列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRoles(Long id, List<Role> roles) {
        deleteUserRoles(id);
        for (Role role : roles) {
            insertUserRole(id, role);
        }
    }

}
