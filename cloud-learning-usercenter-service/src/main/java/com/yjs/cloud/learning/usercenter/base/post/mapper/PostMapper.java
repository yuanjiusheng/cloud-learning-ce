package com.yjs.cloud.learning.usercenter.base.post.mapper;


import com.yjs.cloud.learning.usercenter.base.post.entity.Post;
import com.yjs.cloud.learning.usercenter.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * 岗位Mapper
 * @author bill.lai
 * @since 2019/6/20 10:55
 */
@Repository
public interface PostMapper extends IBaseMapper<Post> {

    /**
     * 删除所有的岗位数据
     */
    @Delete("delete from t_post")
    int deleteAll();

}
