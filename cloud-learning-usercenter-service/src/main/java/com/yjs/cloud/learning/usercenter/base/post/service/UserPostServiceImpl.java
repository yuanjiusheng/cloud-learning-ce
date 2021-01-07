package com.yjs.cloud.learning.usercenter.base.post.service;


import com.yjs.cloud.learning.usercenter.base.post.entity.UserPostRelation;
import com.yjs.cloud.learning.usercenter.base.post.mapper.UserPostRelationMapper;
import com.yjs.cloud.learning.usercenter.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户与岗位关系服务实现
 * @author bill.lai
 * @since 2019/6/19 17:59
 */
@AllArgsConstructor
@Service
public class UserPostServiceImpl extends BaseServiceImpl<UserPostRelationMapper, UserPostRelation> implements UserPostService {

    private final UserPostRelationMapper userPostMapper;

    /**
     * 删除所有
     * @return 删除数量
     */
    @Override
    public int deleteAll(){
        return userPostMapper.deleteAll();
    }

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return 删除数量
     */
    @Override
    public int deleteByUserId(Long userId){
        return userPostMapper.deleteByUserId(userId);
    }

}

