package com.yjs.cloud.learning.auth.biz.role.mapper;

import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.role.bean.RoleResponse;
import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import com.yjs.cloud.learning.auth.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Role Mapper
 *
 * @author Andrew.xiao
 * @since 2019/6/9
 */
@Repository
public interface RoleMapper extends IBaseMapper<Role> {

    /**
     * 删除角色下的权限
     * @param id 角色id
     */
    @Delete("delete from t_role_authority where role_id = #{id}")
    void deleteRolePermissions(Long id);

    /**
     * 为角色添加权限
     * @param id 角色id
     * @param authority 权限
     */
    @Insert("insert into t_role_authority (role_id, authority_id) values (#{id}, #{authority.id})")
    void insertRolePermissions(@Param("id") Long id, @Param("authority") Authority authority);

    /**
     * 获取用户下的角色
     * @param userId 用户id
     * @return 用户角色列表
     */
    @Select("select r.id, r.code, r.name from t_role r " +
            "join t_user_role ur on r.id = ur.role_id " +
            "where user_id = #{userId} ")
    List<RoleResponse> selectByUserId(Long userId);

    /**
     * 保存用户的角色
     * @param id 用户id
     * @param role 角色id
     */
    @Insert("insert into t_user_role (user_id, role_id) values (#{id}, #{role.id})")
    void insertUserRole(@Param("id") Long id, @Param("role") Role role);

    /**
     * 删除用户下的角色
     * @param id 用户id
     */
    @Select("delete from t_user_role where user_id = #{id}")
    void deleteUserRoles(Long id);

}
