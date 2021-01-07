package com.yjs.cloud.learning.auth.biz.authority.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.authority.mapper.AuthorityMapper;
import com.yjs.cloud.learning.auth.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.auth.common.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 权限服务实现
 *
 * @author Andrew.xiao
 * @since 2019/6/6
 */
@AllArgsConstructor
@Service
public class AuthorityServiceImpl extends BaseServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

    private final AuthorityMapper authorityMapper;

    /**
     * 获取用户权限
     * @param userId 用户id
     * @return 用户权限列表
     */
    @Override
    public List<Authority> getByUserId(Long userId) {
        return authorityMapper.selectByUserId(userId);
    }

    /**
     * 是否拥有authorityName权限
     * @param userId 用户名
     * @param authorityName 权限名
     * @return 是否拥有
     */
    @Override
    public Boolean hasAuthority(Long userId, String authorityName){
        List<Authority> authorityList = getByUserId(userId);
        if(!CollectionUtils.isEmpty(authorityList)){
            for (Authority authority : authorityList) {
                if(authority.getName().equals(authorityName)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 返回全部的权限(供树形结构)
     * @param authorities 所有权限
     * @return 树形结构数据
     */
    @Override
    public List<Map<String,Object>> getTree(List<Authority> authorities) {
        List<Map<String,Object>> list = new LinkedList<>();
        authorities.forEach(authority -> {
                if (authority!=null){
                    List<Authority> authorityList = this.list(new QueryWrapper<Authority>().lambda().eq(Authority::getPid, authority.getId()));
                    Map<String,Object> map = new HashMap<>(16);
                    map.put("id",authority.getId());
                    map.put("label",authority.getAlias());
                    map.put("alias",authority.getAlias());
                    map.put("name",authority.getName());
                    map.put("pid",authority.getPid());
                    map.put("createTime", DateUtils.format(authority.getCreateTime()));
                    map.put("updateTime", DateUtils.format(authority.getUpdateTime()));
                    if(authorityList !=null && authorityList.size()!=0){
                        map.put("children",getTree(authorityList));
                    }
                    list.add(map);
                }
            }
        );
        return list;
    }

    /**
     * 根据角色id获取角色下的权限
     * @param roleId 角色id
     * @return 角色下的权限列表
     */
    @Override
    public List<Authority> getByRoleId(Long roleId) {
        return authorityMapper.selectByRoleId(roleId);
    }

}
