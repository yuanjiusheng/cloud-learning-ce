package com.yjs.cloud.learning.usercenter.base.user.service;


import com.yjs.cloud.learning.usercenter.base.user.entity.UserManagerRelation;
import com.yjs.cloud.learning.usercenter.base.user.mapper.UserManagerMapper;
import com.yjs.cloud.learning.usercenter.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 * @author bill.lai
 * @since 2019/6/19 17:59
 */
@AllArgsConstructor
@Service
public class UserManagerServiceImpl extends BaseServiceImpl<UserManagerMapper, UserManagerRelation> implements UserManagerService  {

    private UserManagerMapper userManagerMapper;

    /**
     * 删除所有
     * @return 删除数量
     */
    @Override
    public int deleteAll(){
        return userManagerMapper.deleteAll();
    }

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return 删除数量
     */
    @Override
    public int deleteByUserId(Long userId){
        return userManagerMapper.deleteByUserId(userId);
    }

}

