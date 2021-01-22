package com.yjs.cloud.learning.auth.biz.jwt.bean;

import lombok.Data;

/**
 * 会员信息
 *
 * @author Andrew.xiao
 * @since 2020/7/22
 */
@Data
public class SecurityMemberInfo {

    /**
     * 会员id
     */
    private Long id;

    /**
     * 会员电话
     */
    private String mobile;

    /**
     * 验证码
     */
    private String authCode;
}
