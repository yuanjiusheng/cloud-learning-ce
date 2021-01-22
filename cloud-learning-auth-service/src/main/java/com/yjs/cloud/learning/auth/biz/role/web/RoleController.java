package com.yjs.cloud.learning.auth.biz.role.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.role.bean.*;
import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import com.yjs.cloud.learning.auth.biz.role.service.RoleService;
import com.yjs.cloud.learning.auth.common.controller.BaseController;
import com.yjs.cloud.learning.auth.common.util.StringUtils;
import com.yjs.cloud.learning.auth.common.web.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色控制器
 *
 * @author bill.lai
 * @since 2021/01/18
 */
@Api(tags = "角色管理")
@AllArgsConstructor
@RestController
public class RoleController extends BaseController {

    private final RoleService roleService;

    @ApiOperation(value = "获取角色列表", notes = "获取角色列表", httpMethod = "GET")
    @GetMapping("/role/list")
    public List<RoleResponse> list() {
        List<Role> list = roleService.list();
        List<RoleResponse> responseList = new ArrayList<>();
        for (Role role : list) {
            responseList.add(role.convert());
        }
        return responseList;
    }

    @ApiOperation(value = "获取角色分页列表", notes = "获取角色分页列表", httpMethod = "GET")
    @GetMapping("/role/page/list")
    public RoleGetPageResponse page(RoleGetPageRequest roleGetPageRequest) {
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

    @ApiOperation(value = "创建角色", notes = "创建角色", httpMethod = "POST")
    @PostMapping("/role")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse createRole(@RequestBody RoleCreateRequest roleCreateRequest) {
        if (StringUtils.isEmpty(roleCreateRequest.getName())) {
            throw new GlobalException("名称为必填项");
        }
        if (StringUtils.isEmpty(roleCreateRequest.getCode())) {
            throw new GlobalException("编号为必填项");
        }
        Role role = roleCreateRequest.convert();
        roleService.save(role);
        return role.convert();
    }

    @ApiOperation(value = "修改角色", notes = "修改角色", httpMethod = "PUT")
    @PutMapping("/role")
    public RoleResponse updateRole(@RequestBody RoleUpdateRequest roleUpdateRequest) {
        if (roleUpdateRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        Role role = roleService.getById(roleUpdateRequest.getId());
        if (role == null) {
            throw new GlobalException("角色不存在");
        }
        if (StringUtils.isEmpty(roleUpdateRequest.getName())) {
            throw new GlobalException("名称为必填项");
        }
        if (StringUtils.isEmpty(roleUpdateRequest.getCode())) {
            throw new GlobalException("编号为必填项");
        }
        role.setCode(roleUpdateRequest.getCode());
        role.setName(roleUpdateRequest.getName());
        role.setRemark(roleUpdateRequest.getRemark());
        roleService.updateById(role);
        return role.convert();
    }

    @ApiOperation(value = "删除角色", notes = "删除角色", httpMethod = "DELETE")
    @DeleteMapping("/role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@RequestBody RoleDeleteRequest roleDeleteRequest) {
        if (roleDeleteRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        Role role = roleService.getById(roleDeleteRequest.getId());
        if (role == null) {
            throw new GlobalException("角色不存在");
        }
        roleService.removeById(roleDeleteRequest.getId());
    }

    @ApiOperation(value = "获取角色下的权限", notes = "获取角色下的权限", httpMethod = "GET")
    @GetMapping(value = "/role/authority/list")
    public List<Authority> getRoleAuthorityList(RoleGetAuthorityListRequest roleGetAuthorityListRequest){
        return roleService.getRoleAuthorityList(roleGetAuthorityListRequest);
    }

    @ApiOperation(value = "更新角色下的权限", notes = "更新角色下的权限", httpMethod = "PUT")
    @PutMapping(value = "/role/authority/update")
    public void updateRoleAuthorityList(@RequestBody RoleAuthorityUpdateRequest roleAuthorityUpdateRequest){
        roleService.updateRoleAuthorityList(roleAuthorityUpdateRequest);
    }

    @ApiOperation(value = "获取用户的角色", notes = "获取用户的角色", httpMethod = "GET")
    @GetMapping("/role/user/list")
    public List<RoleResponse> getUserRoleList(RoleUserGetListRequest roleUserGetListRequest) {
        return roleService.getUserRoleList(roleUserGetListRequest);
    }

    @ApiOperation(value = "修改用户的角色", notes = "修改用户的角色", httpMethod = "PUT")
    @PutMapping("/role/user/list")
    public void updateUserRoleList(@RequestBody RoleUserUpdateRequest roleUserUpdateRequest) {
        roleService.updateUserRoleList(roleUserUpdateRequest);
    }
}
