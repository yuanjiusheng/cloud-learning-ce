package com.yjs.cloud.learning.auth.biz.authority.constant;

/**
 * 权限常量
 * @author Bill.lai
 * @since 2019-08-05
 */
public final class AuthConstant {

    /**
     * 用户管理
     */
    public static final String SYSTEM_USER = "hasAuthority('system_user')";

    /**
     * 权限管理
     */
    public static final String SYSTEM_AUTHORITY = "hasAuthority('system_authority')";

    /**
     * 角色管理
     */
    public static final String SYSTEM_ROLE = "hasAuthority('system_role')";

    /**
     * 用户同步
     */
    public static final String SYSTEM_SYNC = "hasAuthority('system_sync')";

    /**
     * 排班接口权限
     */
    public static final String ARRANGEMENT = "hasAuthority('arrangement')";

    /**
     * 排班接口超级权限
     */
    public static final String ADMIN_ARRANGEMENT = "hasAuthority('admin_arrangement')";

    /**
     * 排班表审批
     */
    public static final String APPROVAL = "hasAuthority('approval')";

    /**
     * 班次管理
     */
    public static final String SHIFT = "hasAuthority('shift')";

    /**
     * 班次规则
     */
    public static final String SHIFT_RULE = "hasAuthority('shift_rule')";

    /**
     * 班次规则超级权限
     */
    public static final String ADMIN_SHIFT_RULE = "hasAuthority('admin_shift_rule')";

    /**
     * 统计分析
     */
    public static final String STATISTIC = "hasAuthority('statistic')";

    /**
     * 文件存档
     */
    public static final String ARRANGEMENT_ARCHIVE = "hasAuthority('arrangement_archive')";

    /**
     * 实时上班
     */
    public static final String REAL_TIME_WORKER = "hasAuthority('real_time_worker')";

}
