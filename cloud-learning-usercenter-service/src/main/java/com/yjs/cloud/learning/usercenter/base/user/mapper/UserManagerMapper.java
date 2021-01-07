package com.yjs.cloud.learning.usercenter.base.user.mapper;

import com.yjs.cloud.learning.usercenter.base.user.entity.UserManagerRelation;
import com.yjs.cloud.learning.usercenter.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 岗位Mapper
 *
 * @author bill.lai
 * @since 2019/6/20 10:55
 */
@Repository
public interface UserManagerMapper extends IBaseMapper<UserManagerRelation> {

    /**
     * 删除所有的上级关系表数据
     */
    @Delete("delete from t_user_manager")
    int deleteAll();

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return 删除数量
     */
    @Delete("delete from t_user_manager where user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);
}
