package com.yjs.cloud.learning.usercenter.base.user.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListRequest;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.base.user.entity.User;
import com.yjs.cloud.learning.usercenter.base.user.entity.UserVo;
import com.yjs.cloud.learning.usercenter.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门Mapper
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@Repository
public interface UserMapper extends IBaseMapper<User> {

    /**
     * 获取用户列表
     * @param page 分页对象
     * @param userGetListRequest 请求参数
     * @return 用户列表
     */
    @Select({"<script>" ,
            "select " +
            "   u.id, u.update_time, u.create_time, u.username, u.code, u.name, u.status, u.gender, " ,
            "   u.telephone, u.mobile, u.email, u.birthday, u.id_card, u.nation, u.native_place, u.id_card_address, " ,
            "   u.current_address, u.marital_status, u.contract_start_date, u.contract_end_date, " +
            "   td.name department_name, td.id department_id " ,
            "from t_user u " ,
            "join t_user_department ud on u.id = ud.user_id " ,
            "join t_department td on td.id = ud.department_id " +
            "join t_department_department dd on dd.child_department_id = td.id and (dd.father_department_id = #{req.departmentId} " +
            "<if test='req.fetchAll == null or req.fetchAll == false'>and dd.is_sub = 1 </if> or (dd.child_department_id = #{req.departmentId} and is_sub = 1)) " +
            "where 1 = 1 " ,
            "   <if test='req.keyword != null and req.keyword != \"\"'>",
            "       and (" +
            "              u.mobile like concat('%', #{req.keyword}, '%') " +
            "           or u.name like concat('%', #{req.keyword}, '%') " +
            "           or u.code like concat('%', #{req.keyword}, '%') " +
            "       ) " ,
            "   </if>",
            "</script>"})
    Page<UserResponse> findList(Page<UserResponse> page, @Param("req") UserGetListRequest userGetListRequest);



    @Select("select id, name, password, code, full_name, state, gender, telephone, mobile, email, birthday, " +
            "id_card, nation, native_place, id_card_place, location_place, marital_status, start_date, end_date, " +
            "create_time, update_time from t_user where state in (0, 1, 3) and name = #{name}")
    User selectByName(@Param("name") String name);

    @Select("select id, name, password, code, full_name, state, gender, telephone, mobile, email, birthday, " +
            "id_card, nation, native_place, id_card_place, location_place, marital_status, start_date, end_date, " +
            "create_time, update_time from t_user where state in (0, 1, 3) and code = #{code}")
    User selectByCode(@Param("code") String code);

    @Select("select tu.id, tu.name, tu.password, tu.code, tu.full_name, tu.state, tu.gender, tu.telephone, tu.mobile, " +
            "tu.email, tu.birthday, tu.id_card, tu.nation, tu.native_place, tu.id_card_place, tu.location_place, " +
            "tu.marital_status, tu.start_date, tu.end_date, tu.create_time, tu.update_time from t_user tu " +
            "inner join t_user_dd_id tud on tud.user_id = tu.id " +
            "where tu.state in (0, 1, 3) and tud.dd_id = #{ddId}")
    User selectByDdId(@Param("ddId") String ddId);

    /**
     * 删除除了id = 0的所有的用户数据
     */
    @Delete("delete from t_user where id > 0")
    int deleteAll();

    /**
     * 根据部门获取用户
     * @param departmentId  部门id
     * @return 用户列表
     */
    @Select({"<script>",
            "select u.id, u.code, u.update_time, u.create_time, u.birthday, " +
                    "u.email, u.end_date, u.id_card, u.full_name, u.nation, " +
                    "u.location_place, u.marital_status, u.mobile, " +
                    "u.name, u.native_place, u.id_card_place, " +
                    "u.gender, u.start_date, u.state,  p.full_name postName, " +
                    "   td.name departmentName, td.id departmentId " ,
                    "from t_user u join " +
                    "t_user_department ud on u.id = ud.user_id " +
                    "join t_department td on td.id = ud.department_id " +
                    "join t_user_post up on u.id = up.user_id " +
                    "join t_post p on p.id = up.post_id " +
                    "where " +
                    "<choose>" +
                    "<when test='searchText != null and searchText != \"\"'>" +
                    "u.full_name like #{searchText} and ",
                    "</when>" +
                    "<otherwise>" +
                    "ud.department_id = #{departmentId} and " +
                    "</otherwise>" +
                    "</choose>" +
                    "state in(0, 1, 3) ",
            "</script>"})
    IPage<User> selectByDepartmentId(Page<User> page,
                                     @Param("departmentId") Long departmentId,
                                     @Param("searchText") String searchText
    );

    /**
     * 获取所有用户信息
     */
    @Select({"<script>" ,
            "select u.id, u.code, u.update_time, u.create_time, u.birthday, " ,
            "   u.email, u.end_date, u.id_card, u.full_name, u.nation, " ,
            "   u.location_place, u.marital_status, u.mobile, " ,
            "   u.name, u.native_place, u.id_card_place, " ,
            "   u.gender, u.start_date, u.end_date, u.state,  p.full_name postName, " +
            "   td.name departmentName, td.id departmentId " ,
            "from t_user u " ,
            "join t_user_department ud on u.id = ud.user_id " ,
            "join t_department td on td.id = ud.department_id " +
                    "join t_user_post up on u.id = up.user_id " ,
            "join t_post p on p.id = up.post_id " ,
            "where u.state in (0, 1, 3)" ,
            "<if test='departmentIdList != null and departmentIdList.size() > 0'>",
            "   and ud.department_id in " ,
            "   <foreach collection='departmentIdList' item='item' index='index' open='(' separator=',' close=')'> " ,
            "       #{item} ",
            "   </foreach>" ,
            "</if>",
            "<if test='userSearchKey != null and userSearchKey != \"\"'>" +
                    " and u.full_name like #{userSearchKey} ",
            "</if>" +
                    "</script>"})
    List<User> selectUser(@Param("departmentIdList") List<Long> departmentIdList, @Param("userSearchKey") String userSearchKey);

    /**
     * 获取用户信息
     * @param userId 用户id
     */
    @Select("select u.id, u.code, u.update_time, u.create_time, u.birthday, " +
            "   u.email, u.end_date, u.id_card, u.full_name, u.nation, " +
            "   u.location_place, u.marital_status, u.mobile, " +
            "   u.name, u.native_place, u.id_card_place, " +
            "   u.gender, u.start_date, u.state,  p.full_name postName, d.name departmentName, d.id departmentId " +
            "from t_user u " +
            "join t_user_department ud on u.id = ud.user_id " +
            "join t_department d on d.id = ud.department_id " +
            "join t_user_post up on u.id = up.user_id " +
            "join t_post p on p.id = up.post_id " +
            "where u.state in (0, 1, 3) and u.id=#{userId}")
    User selectUserById(@Param("userId") Long userId);

    /**
     * 获取店铺（部门）的所有员工
     * @param departmentId 店铺（部门）id
     * @param userId 用户id
     * @param searchFullName 用户名称搜索关键字
     * @param searchName 搜索登录名
     * @return 员工信息列表
     */
    @Select({"<script>" ,
            "select tu.id user_id, tu.full_name user_name, td.name department_name, tp.full_name post_name, tu.start_date " +
            " from t_user tu " +
            "inner join t_user_department tud on tu.id = tud.user_id " +
            "inner join t_department td on td.id = tud.department_id " +
            "inner join t_user_post tup on tu.id = tup.user_id " +
            "inner join t_post tp on tup.post_id = tp.id " +
            "where tu.state in (0, 1, 3) " +
            "   <if test='departmentId != null'>",
            "       and td.id = #{departmentId} " ,
            "   </if>",
            "   <if test='searchFullName != null and searchFullName != \"\"'>",
            "       and tu.full_name like #{searchFullName} ",
            "   </if>",
            "   <if test='searchName != null and searchName != \"\"'>",
            "       and (tu.name like #{searchName} or tu.id like #{searchName}) ",
            "   </if>",
            "   <if test='userId != null'>",
            "       and tu.id = #{userId} ",
            "   </if>",
            "</script>"
    })
    List<UserVo> selectUserVo(@Param("departmentId") Long departmentId, @Param("searchFullName") String searchFullName,
                                            @Param("userId") Long userId, @Param("searchName") String searchName);

    /**
     * 根据部门获取用户
     * @param departmentIdList  部门id
     * @param fetchAll fetchAll 是否获取所有子部门用户（包括孙子部门用户...）
     * @return 用户列表
     */
    @Select({"<script>" ,
            "select u.id, u.code, u.update_time, u.create_time, u.birthday, " ,
            "   u.email, u.end_date, u.id_card, u.full_name, u.nation, " ,
            "   u.location_place, u.marital_status, u.mobile, " ,
            "   u.name, u.native_place, u.id_card_place, " ,
            "   u.gender, u.start_date, u.state,  " +
                    "   td.name departmentName, td.id departmentId " ,
            "from t_user u " ,
            "join t_user_department ud on u.id = ud.user_id " ,
            "join t_department td on td.id = ud.department_id and td.enabled = 1 " +
            "join t_department_department dd on dd.child_department_id = td.id and (dd.father_department_id in " +
            "   <foreach collection='departmentIdList' item='item' index='index' open='(' separator=',' close=')'> " ,
            "       #{item} ",
            "   </foreach>" ,
            "<if test='fetchAll == null or fetchAll == false'> and dd.is_sub = 1 </if> or (is_sub = 1 and dd.child_department_id in  " +
            "   <foreach collection='departmentIdList' item='item' index='index' open='(' separator=',' close=')'> " ,
            "       #{item} ",
            "   </foreach>" ,
            " )) " ,
            "where u.state in (0, 1, 3)" ,
            "</script>"})
    List<User> findUsers(@Param("departmentIdList") List<Long> departmentIdList, @Param("fetchAll") Boolean fetchAll);

    /**
     * 根据部门，关键字（手机，工号，姓名）获取用户信息
     * @param departmentId 部门
     * @param searchText 关键字
     * @return 用户列表
     */
    @Select({"<script>" ,
            "select distinct u.id, u.name, u.full_name, u.mobile, d.short_name department_name" +
                    " from t_user u " +
                    "join t_user_department ud on u.id = ud.user_id " +
                    "join t_department d on ud.department_id = d.id " +
                    "join t_department_department dd  on d.id = dd.child_department_id " +
                    "where " +
                    "state in (0, 1, 3) and (father_department_id = #{departmentId} or d.id = #{departmentId}) " +
                    "   <if test='searchText != null and searchText != \"\"'>",
                    "       and (u.mobile = #{searchText} or u.full_name like #{searchText} or u.code like #{searchText}) " ,
                    "   </if>",
            "</script>"
    })
    List<User> getUserByDepartmentIdAndKeyword(@Param("departmentId")Long departmentId, @Param("searchText")String searchText);
}
