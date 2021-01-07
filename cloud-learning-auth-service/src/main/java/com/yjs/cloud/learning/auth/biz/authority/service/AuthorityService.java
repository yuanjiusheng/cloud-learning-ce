package com.yjs.cloud.learning.auth.biz.authority.service;


import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 权限服务
 *
 * @author Andrew.xiao
 * @since 2019/6/6
 */
public interface AuthorityService extends IBaseService<Authority> {
    /**
     * 获取用户权限
     * @param userId 用户id
     * @return 用户权限列表
     */
    List<Authority> getByUserId(Long userId);

    /**
     * 是否拥有authorityName权限
     * @param userId 用户名
     * @param authorityName 权限名
     * @return 是否拥有
     */
    Boolean hasAuthority(Long userId, String authorityName);

    /**
     * 返回全部的权限(供树形结构)
     * @param authorities 所有权限
     * @return 树形结构数据
     */
    List<Map<String,Object>> getTree(List<Authority> authorities);

    /**
     * 根据角色id获取角色下的权限
     * @param roleId 角色id
     * @return 角色下的权限列表
     */
    List<Authority> getByRoleId(Long roleId);
}
