package com.yjs.cloud.learning.usercenter.base.user.entity;

import lombok.Data;

/**
 * User
 *
 * @author Andrew.xiao
 * @since 2019/6/6
 */
@Data
public class UserVo {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 岗位名称
     */
    private String postName;

}
