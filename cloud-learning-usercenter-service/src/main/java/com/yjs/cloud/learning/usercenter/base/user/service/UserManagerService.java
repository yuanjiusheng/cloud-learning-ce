package com.yjs.cloud.learning.usercenter.base.user.service;


import com.yjs.cloud.learning.usercenter.base.user.entity.UserManagerRelation;
import com.yjs.cloud.learning.usercenter.common.service.IBaseService;

/**
 * 用户与上级领导Service
 * @author bill.lai
 * @since 2019/6/19 17:58
 */
public interface UserManagerService extends IBaseService<UserManagerRelation> {

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
