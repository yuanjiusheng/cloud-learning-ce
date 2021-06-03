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
    List<Department> getList(@Param("departmentIdList") List<Long> departmentIdList, @Param("fetchAll") Boolean fetchAll);

}
