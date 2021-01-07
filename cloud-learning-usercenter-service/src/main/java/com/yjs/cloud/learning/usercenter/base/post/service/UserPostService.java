package com.yjs.cloud.learning.usercenter.base.post.service;


import com.yjs.cloud.learning.usercenter.base.post.entity.UserPostRelation;
import com.yjs.cloud.learning.usercenter.common.service.IBaseService;

/**
 * 用户与岗位关系Service
 * @author bill.lai
 * @since 2019/6/19 17:58
 */
public interface UserPostService extends IBaseService<UserPostRelation> {

    /**
     * 删除所有
     * @return 删除数量
     */
    int deleteAll();

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return 删除数量
     */
    int deleteByUserId(Long userId);
}

