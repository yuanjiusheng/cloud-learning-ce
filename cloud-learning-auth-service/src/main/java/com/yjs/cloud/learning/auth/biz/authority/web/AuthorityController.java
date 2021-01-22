package com.yjs.cloud.learning.auth.biz.authority.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjs.cloud.learning.auth.biz.authority.bean.AuthorityGetPageRequest;
import com.yjs.cloud.learning.auth.biz.authority.bean.AuthorityGetPageResponse;
import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.authority.service.AuthorityService;
import com.yjs.cloud.learning.auth.common.controller.BaseController;
import com.yjs.cloud.learning.auth.common.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
public class AuthorityController extends BaseController {

    private final AuthorityService authorityService;

    @ApiOperation(value = "获取所有权限列表", notes = "获取所有权限列表", httpMethod = "GET")
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

}
