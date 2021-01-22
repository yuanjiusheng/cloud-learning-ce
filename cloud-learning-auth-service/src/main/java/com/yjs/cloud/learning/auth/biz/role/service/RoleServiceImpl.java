package com.yjs.cloud.learning.auth.biz.role.service;

import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.authority.service.AuthorityService;
import com.yjs.cloud.learning.auth.biz.role.bean.*;
import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import com.yjs.cloud.learning.auth.biz.role.mapper.RoleMapper;
import com.yjs.cloud.learning.auth.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.auth.common.web.GlobalException;
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
     * 获取角色的权限列表
     * @param roleGetAuthorityListRequest 请求参数
     * @return 角色的权限列表
     */
    @Override
    public List<Authority> getRoleAuthorityList(RoleGetAuthorityListRequest roleGetAuthorityListRequest) {
        if (roleGetAuthorityListRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        return authorityService.getByRoleId(roleGetAuthorityListRequest.getId());
    }

    /**
     * 更新角色下的权限
     * @param roleAuthorityUpdateRequest 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleAuthorityList(RoleAuthorityUpdateRequest roleAuthorityUpdateRequest) {
        if (roleAuthorityUpdateRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        roleMapper.deleteRolePermissions(roleAuthorityUpdateRequest.getId());
        for (Authority authority : roleAuthorityUpdateRequest.getAuthorityIdList()) {
            roleMapper.insertRolePermissions(roleAuthorityUpdateRequest.getId(), authority);
        }
    }

    /**
     * 获取用户的角色
     * @param roleUserGetListRequest 请求参数
     * @return 用户角色列表
     */
    @Override
    public List<RoleResponse> getUserRoleList(RoleUserGetListRequest roleUserGetListRequest) {
        if (roleUserGetListRequest.getUserId() == null) {
            throw new GlobalException("userId为必填项");
        }
        return roleMapper.selectByUserId(roleUserGetListRequest.getUserId());
    }

    /**
     * 更新用户的角色
     * @param roleUserUpdateRequest 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRoleList(RoleUserUpdateRequest roleUserUpdateRequest) {
        roleMapper.deleteUserRoles(roleUserUpdateRequest.getUserId());
        for (Role role : roleUserUpdateRequest.getRoleList()) {
            roleMapper.insertUserRole(roleUserUpdateRequest.getUserId(), role);
        }
    }

}
