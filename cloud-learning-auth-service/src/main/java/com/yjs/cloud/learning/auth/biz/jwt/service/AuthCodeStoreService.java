package com.yjs.cloud.learning.auth.biz.jwt.service;

/**
 * 动态密码存储服务
 *
 * @author Andrew.xiao
 * @since 2020/7/22
 */
public interface AuthCodeStoreService {

    /**
     * 保存手机号码和动态密码到缓存
     * @param mobilePhone 手机号码
     * @param authCode 动态密码
     */
    void save(String mobilePhone, String authCode);

    /**
     * 根据手机号码获取动态密码
     * @param mobilePhone 手机号码
     * @return 会员信息
     */
    String get(String mobilePhone);

}
