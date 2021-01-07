package com.yjs.cloud.learning.usercenter.base.department.service;


import com.yjs.cloud.learning.usercenter.base.department.entity.UserDepartmentRelation;
import com.yjs.cloud.learning.usercenter.common.service.IBaseService;

/**
 * 用户与部门关系Service
 * @author bill.lai
 * @since 2019/6/19 17:58
 */
public interface UserDepartmentService extends IBaseService<UserDepartmentRelation> {

    /**
     * 删除全部
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
