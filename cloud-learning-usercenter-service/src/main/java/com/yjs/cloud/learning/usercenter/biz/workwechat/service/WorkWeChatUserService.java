package com.yjs.cloud.learning.usercenter.biz.workwechat.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 企业微信用户服务
 *
 * @author Bill.lai
 * @since 2020/5/29
 */
public interface WorkWeChatUserService {

    /**
     * 获取用户信息
     * @param code 通过成员授权获取到的code，code只能使用一次，5分钟未被使用自动过期。
     * @return 用户信息
     */
    JSONObject getByCode(String code);

    /**
     * 获取用户信息
     * @param userId 企业微信用户id
     * @return 用户信息
     */
    JSONObject getById(String userId);
}
