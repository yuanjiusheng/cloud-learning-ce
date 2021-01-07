package com.yjs.cloud.learning.usercenter.base.post.mapper;

import com.yjs.cloud.learning.usercenter.base.post.entity.UserPostRelation;
import com.yjs.cloud.learning.usercenter.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户与岗位关系Mapper
 * @author bill.lai
 * @since 2019/6/20 10:55
 */
@Repository
public interface UserPostRelationMapper extends IBaseMapper<UserPostRelation> {


    /**
     * 删除所有的岗位关系表数据
     */
    @Delete("delete from t_user_post")
    int deleteAll();

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return 删除数量
     */
    @Delete("delete from t_user_post where user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);
}

