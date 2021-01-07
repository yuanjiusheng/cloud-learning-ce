package com.yjs.cloud.learning.usercenter.base.department.mapper;

import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListRequest;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentResponse;
import com.yjs.cloud.learning.usercenter.base.department.entity.Department;
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
public interface DepartmentMapper extends IBaseMapper<Department> {

    /**
     * 获取部门列表
     * @param departmentGetListRequest 参数
     * @return 部门列表
     */
    @Select("<script>" +
            "select " +
            "   d.id, dd.direct_father_department_id pid, d.name, d.code, " +
            "   d.enabled, d.create_time, d.update_time " +
            "from t_department d " +
            "join t_department_department dd on d.id = dd.child_department_id " +
            "where " +
            "   dd.father_department_id = #{request.id} and d.enabled = 1 " +
            "   <if test='request.fetchAll == null or request.fetchAll == false'>and dd.is_sub = 1 </if>" +
            "</script>")
    List<DepartmentResponse> findList(@Param("request") DepartmentGetListRequest departmentGetListRequest);

    @Select("select t.id, t.code, t.name, td.dd_id as dingTalkId, t.enabled, t.create_time, t.update_time " +
            "from t_department t " +
            "join t_department_dd_id td on td.department_id=t.id" +
            " where td.dd_id = #{dingTalkDeptId}")
    Department selectByDingTalkId(@Param("dingTalkDeptId") Long dingTalkDeptId);

    @Select("select t.id, t.code, t.name, td.dd_id as dingTalkId, t.enabled, t.create_time, t.update_time " +
            " from t_department t" +
            " left join t_department_dd_id td on td.department_id = t.id" +
            " where name = #{name} and enabled = 1")
    Department selectByName(@Param("name") String name);

    @Select({
            "<script>",
            "select t.id, t.code, t.name,  td.dd_id as dingTalkId, t.enabled, t.create_time, t.update_time from t_department t",
            " left join t_department_dd_id td on td.department_id=t.id",
            " where ",
            "   t.name in ",
            "   <foreach collection='nameList' item='item' index='index' open='(' separator=',' close=')'> ",
            "       #{item} ",
            "   </foreach>",
            "</script>"
    })
    List<Department> selectByNameList(@Param("nameList") List<String> nameList);

    /**
     * 删除所有的部门数据
     */
    @Delete("delete from t_department")
    int deleteAll();


    @Select("select d.id, d.code, d.short_name, d.name, d.create_time, d.update_time from t_department d " +
            "inner join t_user_department tud on tud.department_id = d.id " +
            "inner join t_user tu on tu.id = tud.user_id " +
            "where tu.id = #{userId}")
    Department selectByUserId(@Param("userId") Long userId);

    @Select("select d.id, d.short_name, d.name, d.create_time, d.update_time from t_department d where enabled = 1")
    List<Department> selectAll();

    @Select({
            "<script>select distinct d.id, dd.direct_father_department_id pid ,d.short_name, d.name, d.enabled, d.create_time, d.update_time from t_department d " +
            "join t_department_department dd on d.id = dd.child_department_id " +
            "where (dd.father_department_id in " +
            "   <foreach collection='departmentIdList' item='item' index='index' open='(' separator=',' close=')'> ",
            "       #{item} ",
            "   </foreach>",
            "   <if test='fetchAll == null or fetchAll == false'>and dd.is_sub = 1 </if> or " +
            "   dd.is_sub = 1 and dd.child_department_id in  " +
            "   <foreach collection='departmentIdList' item='item' index='index' open='(' separator=',' close=')'> " ,
            "       #{item} ",
            "   </foreach>" ,
            " ) " +
            "and d.enabled = 1 " +
            "</script>"
    })
    List<Department> selectList(@Param("departmentIdList") List<Long> departmentIdList, @Param("fetchAll") Boolean fetchAll);

    @Select("select d.id, d.short_name, d.name, d.create_time, d.update_time from t_department d " +
            "join t_department_department tdd on d.id = tdd.child_department_id " +
            "where father_department_id = #{userDepartmentId} and child_department_id = #{id} and d.enabled = 1")
    List<Department> selectByUserDepartmentIdAndId(@Param("userDepartmentId") Long userDepartmentId, @Param("id") Long id);

}
