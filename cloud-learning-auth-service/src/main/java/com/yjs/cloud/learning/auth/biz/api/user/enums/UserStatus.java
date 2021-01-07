package com.yjs.cloud.learning.auth.biz.api.user.enums;

/**
 * 用户状态
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
public enum UserStatus {
    /**
     * 试用
     */
    trial,
    /**
     * 试用延期
     */
    trial_extension,
    /**
     * 正式
     */
    official,
    /**
     * 解聘
     */
    dismissal,
    /**
     * 离职
     */
    separation
}
