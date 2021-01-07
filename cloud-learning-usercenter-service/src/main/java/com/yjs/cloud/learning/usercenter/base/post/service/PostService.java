package com.yjs.cloud.learning.usercenter.base.post.service;


import com.yjs.cloud.learning.usercenter.base.post.entity.Post;
import com.yjs.cloud.learning.usercenter.common.service.IBaseService;

/**
 * 岗位Service
 * @author bill.lai
 * @since 2019/6/19 17:58
 */
public interface PostService extends IBaseService<Post> {

    /**
     * 删除所有岗位
     * @return 删除数量
     */
    int deleteAll();
}
