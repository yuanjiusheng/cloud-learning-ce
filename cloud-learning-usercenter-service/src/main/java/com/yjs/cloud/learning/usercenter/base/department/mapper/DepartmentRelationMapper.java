package com.yjs.cloud.learning.usercenter.base.department.mapper;


import com.yjs.cloud.learning.usercenter.base.department.entity.DepartmentRelation;
import com.yjs.cloud.learning.usercenter.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门关系Mapper
 *
 * @author bill.lai
 * @since 2019/6/20 10:55
 */
@Repository
public interface DepartmentRelationMapper extends IBaseMapper<DepartmentRelation> {

    /**
     * 删除所有的部门关系表数据
     */
    @Delete("delete from t_department_department")
    int deleteAll();

    /**
     * 获取id的所有父级部门
     * @param childId 子id
     * @return 列表
     */
    @Select("select child_department_id, father_department_id from t_department_department where child_department_id = #{childId}")
    List<DepartmentRelation> getByChildId(@Param("childId") Long childId);

    /**
     * 根据部门id删除部门关系
     * @param departmentId 部门id
     * @return 删除数量
     */
    @Delete("delete from t_department_department where child_department_id = #{departmentId}")
    Integer deleteByChildId(@Param("departmentId") Long departmentId);

    /**
     * 获取id的父级部门
     * @param childId 子id
     * @return 部门
     */
    @Select("select child_department_id, father_department_id from t_department_department " +
            "where child_department_id = #{childId} and is_sub = 1")
    DepartmentRelation getSubByChildId(@Param("childId") Long childId);
}
