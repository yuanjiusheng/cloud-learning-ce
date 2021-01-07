package com.yjs.cloud.learning.auth.biz.role.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.auth.biz.authority.constant.AuthConstant;
import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.role.bean.RoleGetPageRequest;
import com.yjs.cloud.learning.auth.biz.role.bean.RoleGetPageResponse;
import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import com.yjs.cloud.learning.auth.biz.role.service.RoleService;
import com.yjs.cloud.learning.auth.common.controller.BaseController;
import com.yjs.cloud.learning.auth.common.util.StringUtils;
import com.yjs.cloud.learning.auth.common.web.ResourceNotFoundException;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 角色控制器
 *
 * @author Andrew.xiao
 * @since 2019/6/12
 */
@Api(tags = "角色管理")
@AllArgsConstructor
@RestController
@PreAuthorize(AuthConstant.SYSTEM_ROLE)
public class RoleController extends BaseController {

    private final RoleService roleService;

    /**
     * 获取所有角色
     * @return 角色列表
     */
    @GetMapping("/roles/all")
    public List<Role> all() {
        return roleService.list();
    }

    /**
     * 获取角色分页列表
     * @param roleGetPageRequest 参数
     * @return 角色列表
     */
    @GetMapping("/roles")
    public RoleGetPageResponse listPage(RoleGetPageRequest roleGetPageRequest) {
        IPage<Role> page = new Page<>(roleGetPageRequest.getCurrent(), roleGetPageRequest.getSize());
        if (StringUtils.isEmpty(roleGetPageRequest.getKeyword())) {
            page = roleService.page(page);
        } else {
            page = roleService.page(page, new QueryWrapper<Role>().lambda().like(Role::getName, roleGetPageRequest.getKeyword()));
        }
        RoleGetPageResponse roleGetPageResponse = new RoleGetPageResponse();
        roleGetPageResponse.setList(page.getRecords());
        roleGetPageResponse.setTotal(page.getTotal());
        roleGetPageResponse.setPages(page.getPages());
        roleGetPageResponse.setCurrent(page.getCurrent());
        roleGetPageResponse.setSize(page.getSize());
        return roleGetPageResponse;
    }

    /**
     * 获取单个角色
     * @param id 角色 id
     * @return 角色详情
     */
    @GetMapping(value = "/roles/{id}")
    public Role getRoles(@PathVariable Long id){
        Role role = roleService.getById(id);
        if (role == null) {
            throw new ResourceNotFoundException(id,"role");
        }
        return role;
    }

    /**
     * 获取角色下的权限
     * @param id 角色 id
     * @return 某角色拥有的权限列表
     */
    @GetMapping(value = "/roles/{id}/authorities")
    public List<Authority> getRolePermissions(@PathVariable Long id){
        return roleService.getRolePermissions(id);
    }

    /**
     * 更新角色下的权限
     * @param id 角色 id
     */
    @PutMapping(value = "/roles/{id}/authorities")
    public void updateRolePermissions(@PathVariable Long id, @RequestBody List<Authority> authorities){
        roleService.updateRolePermissions(id, authorities);
    }

    /**
     * 创建角色
     * @param role 角色
     * @return 创建后的角色
     */
    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Role newRole(@RequestBody Role role) {
        roleService.save(role);
        return role;
    }

    /**
     * 修改角色
     * @param id 角色id
     * @param role 角色
     * @return 修改后的角色
     */
    @PutMapping("/roles/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        roleService.updateById(role);
        return role;
    }

    /**
     * 删除角色
     * @param id 角色id
     */
    @DeleteMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable Long id) {
        roleService.removeById(id);
    }

    /**
     * 获取用户下的角色
     * @param id 用户id
     * @return 用户角色列表
     */
    @PreAuthorize(AuthConstant.SYSTEM_USER)
    @GetMapping("users/{id}/roles")
    public List<Role> getUserRoles(@PathVariable("id") Long id) {
        return roleService.getUserRoles(id);
    }

    /**
     * 更新用户下的角色
     * @param id 用户id
     * @param roles 角色列表
     */
    @PreAuthorize(AuthConstant.SYSTEM_USER)
    @PutMapping("users/{id}/roles")
    public void updateUserRoles(@PathVariable("id") Long id, @RequestBody List<Role> roles) {
        roleService.updateUserRoles(id, roles);
    }
}
