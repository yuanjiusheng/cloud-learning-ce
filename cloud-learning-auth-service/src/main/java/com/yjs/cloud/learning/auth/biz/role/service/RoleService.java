package com.yjs.cloud.learning.auth.biz.role.service;

import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.role.bean.*;
import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import com.yjs.cloud.learning.auth.common.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色服务
 *
 * @author bill.lai
 * @since 2019/6/12
 */
public interface RoleService extends IBaseService<Role> {

    /**
     * 获取角色的权限列表
     * @param roleGetAuthorityListRequest 请求参数
     * @return 角色的权限列表
     */
    List<Authority> getRoleAuthorityList(RoleGetAuthorityListRequest roleGetAuthorityListRequest);

    /**
     * 更新角色下的权限
     * @param roleAuthorityUpdateRequest 请求参数
     */
    @Transactional(rollbackFor = Exception.class)
    void updateRoleAuthorityList(RoleAuthorityUpdateRequest roleAuthorityUpdateRequest);

    /**
     * 获取用户的角色
     * @param roleUserGetListRequest 请求参数
     * @return 用户角色列表
     */
    List<RoleResponse> getUserRoleList(RoleUserGetListRequest roleUserGetListRequest);

    /**
     * 更新用户的角色
     * @param roleUserUpdateRequest 请求参数
     */
    @Transactional(rollbackFor = Exception.class)
    void updateUserRoleList(RoleUserUpdateRequest roleUserUpdateRequest);
}
