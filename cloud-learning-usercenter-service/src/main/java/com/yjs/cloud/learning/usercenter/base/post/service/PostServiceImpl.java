package com.yjs.cloud.learning.usercenter.base.post.service;


import com.yjs.cloud.learning.usercenter.base.post.entity.Post;
import com.yjs.cloud.learning.usercenter.base.post.mapper.PostMapper;
import com.yjs.cloud.learning.usercenter.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 岗位服务实现
 * @author bill.lai
 * @since 2019/6/19 17:59
 */
@AllArgsConstructor
@Service
public class PostServiceImpl extends BaseServiceImpl<PostMapper, Post> implements PostService {

    private PostMapper postMapper;

    /**
     * 删除所有岗位
     * @return 删除数量
     */
    @Override
    public int deleteAll(){
        return postMapper.deleteAll();
    }

}
