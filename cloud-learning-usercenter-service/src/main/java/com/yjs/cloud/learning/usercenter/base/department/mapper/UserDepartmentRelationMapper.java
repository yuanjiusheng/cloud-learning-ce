package com.yjs.cloud.learning.usercenter.base.department.mapper;


import com.yjs.cloud.learning.usercenter.base.department.entity.UserDepartmentRelation;
import com.yjs.cloud.learning.usercenter.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户与部门关系Mapper
 * @author bill.lai
 * @since 2019/6/20 10:55
 */
@Repository
public interface UserDepartmentRelationMapper extends IBaseMapper<UserDepartmentRelation> {

    /**
     * 删除所有的部门用户关系表数据
     */
    @Delete("delete from t_user_department")
    int deleteAll();

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return 删除数量
     */
    @Delete("delete from t_user_department where user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);
}
