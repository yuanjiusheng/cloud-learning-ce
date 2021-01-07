package com.yjs.cloud.learning.auth.biz.authority.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjs.cloud.learning.auth.biz.authority.constant.AuthConstant;
import com.yjs.cloud.learning.auth.biz.authority.bean.AuthorityGetPageRequest;
import com.yjs.cloud.learning.auth.biz.authority.bean.AuthorityGetPageResponse;
import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.authority.service.AuthorityService;
import com.yjs.cloud.learning.auth.common.controller.BaseController;
import com.yjs.cloud.learning.auth.common.util.StringUtils;
import com.yjs.cloud.learning.auth.common.web.ResourceNotFoundException;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 权限控制器
 *
 * @author Andrew.xiao
 * @since 2019/6/12
 */
@Api(tags = "权限管理")
@AllArgsConstructor
@RestController
@PreAuthorize(AuthConstant.SYSTEM_AUTHORITY)
public class AuthorityController extends BaseController {

    private final AuthorityService authorityService;

    /**
     * 获取所有权限列表
     * @return 权限列表
     */
    @GetMapping("/authorities")
    public AuthorityGetPageResponse list(AuthorityGetPageRequest authorityGetPageRequest) {
        List<Map<String, Object>> tree = getTree(authorityGetPageRequest.getKeyword());
        AuthorityGetPageResponse authorityGetPageResponse = new AuthorityGetPageResponse();
        authorityGetPageResponse.setTotal(Integer.valueOf(tree.size()).longValue());
        authorityGetPageResponse.setList(tree);
        return authorityGetPageResponse;
    }

    /**
     * 返回全部的权限(供树形结构)
     * @param searchText 搜索内容
     * @return 树形结构数据
     */
    @GetMapping(value = "/authorities/tree")
    public List<Map<String,Object>> getTree(String searchText){
        LambdaQueryWrapper<Authority> lambdaQueryWrapper = new QueryWrapper<Authority>().lambda().eq(Authority::getPid, 0L);
        if (!StringUtils.isEmpty(searchText)) {
            lambdaQueryWrapper.like(Authority::getAlias, searchText);
        }
        List<Authority> authorities = authorityService.list(lambdaQueryWrapper);
        return authorityService.getTree(authorities);
    }

    /**
     * 获取单个权限
     * @param id 权限 id
     * @return 权限详情
     */
    @GetMapping(value = "/authorities/{id}")
    public Authority getPermissions(@PathVariable Long id){
        Authority authority = authorityService.getById(id);
        if (authority == null) {
            throw new ResourceNotFoundException(id, "authority");
        }
        return authority;
    }

    /**
     * 创建权限
     * @param authority 权限
     * @return 创建后的权限
     */
    @PostMapping("/authorities")
    @ResponseStatus(HttpStatus.CREATED)
    public Authority newPermission(@RequestBody Authority authority) {
        authorityService.save(authority);
        return authority;
    }

    /**
     * 修改权限
     * @param id 权限id
     * @param authority 权限
     * @return 修改后的权限
     */
    @PutMapping("/authorities/{id}")
    public Authority updatePermission(@PathVariable Long id, @RequestBody Authority authority) {
        authority.setId(id);
        authorityService.updateById(authority);
        return authority;
    }

    /**
     * 删除权限
     * @param id 权限id
     */
    @DeleteMapping("/authorities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePermission(@PathVariable Long id) {
        authorityService.removeById(id);
    }

}
