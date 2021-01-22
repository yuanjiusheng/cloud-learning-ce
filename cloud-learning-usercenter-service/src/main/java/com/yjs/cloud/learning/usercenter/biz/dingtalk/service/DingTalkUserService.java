package com.yjs.cloud.learning.usercenter.biz.dingtalk.service;

import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.biz.dingtalk.bean.DingTalkGetUserByCodeRequest;

/**
 * 钉钉用户服务
 *
 * @author Bill.lai
 * @since 1/22/21
 */
public interface DingTalkUserService {

    /**
     * 获取授权用户的个人信息
     * @param dingTalkGetUserByCodeRequest 请求参数
     * @return 用户信息
     */
    UserResponse getByCode(DingTalkGetUserByCodeRequest dingTalkGetUserByCodeRequest);
}
