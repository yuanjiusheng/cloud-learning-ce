package com.yjs.cloud.learning.usercenter.base.department.service;


import com.yjs.cloud.learning.usercenter.base.department.entity.UserDepartmentRelation;
import com.yjs.cloud.learning.usercenter.base.department.mapper.UserDepartmentRelationMapper;
import com.yjs.cloud.learning.usercenter.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户与部门关系服务实现
 * @author bill.lai
 * @since 2019/6/19 17:59
 */
@AllArgsConstructor
@Service
public class UserDepartmentServiceImpl extends BaseServiceImpl<UserDepartmentRelationMapper, UserDepartmentRelation> implements UserDepartmentService {

    private UserDepartmentRelationMapper userDepartmentMapper;

    /**
     * 删除全部
     * @return 删除数量
     */
    @Override
    public int deleteAll(){
        return userDepartmentMapper.deleteAll();
    }

    /**
     * 根据用户id删除
     * @param userId 用户id
     * @return 删除数量
     */
    @Override
    public int deleteByUserId(Long userId){
        return userDepartmentMapper.deleteByUserId(userId);
    }

}
