package com.yjs.cloud.learning.auth.biz.jwt.service;

/**
 * auth service
 *
 * @author Andrew.xiao
 * @since 2020/7/22
 */
public interface AuthService {
    /**
     * 获取验证码
     * @param mobilePhone 手机号码
     * @return 验证码
     */
    String getAuthCode(String mobilePhone);
}
