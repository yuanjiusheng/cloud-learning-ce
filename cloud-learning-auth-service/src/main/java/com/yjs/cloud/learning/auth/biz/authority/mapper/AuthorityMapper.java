package com.yjs.cloud.learning.auth.biz.authority.mapper;

import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 权限Mapper
 *
 * @author Andrew.xiao
 * @since 2019/6/6
 */
@Repository
public interface AuthorityMapper extends IBaseMapper<Authority> {

    /**
     * 获取用户权限
     * @param userId 用户id
     * @return 用户权限列表
     */
    @Select("select a.id, a.name from t_authority a " +
            "join t_role_authority ra on a.id = ra.authority_id " +
            "join t_user_role ur on ra.role_id = ur.role_id where ur.user_id = #{userId}")
    List<Authority> selectByUserId(Long userId);

    /**
     * 获取用户权限
     * @param roleId 角色id
     * @return 用户权限列表
     */
    @Select("select a.id, a.pid, a.name, a.alias, a.create_time, a.update_time from t_authority a " +
            "join t_role_authority ra on a.id = ra.authority_id " +
            "where ra.role_id = #{roleId}")
    List<Authority> selectByRoleId(Long roleId);

}
