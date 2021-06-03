package com.yjs.cloud.learning.auth.biz.sso.service;

import com.yjs.cloud.learning.auth.biz.sso.bean.SsoClientCreateRequest;
import com.yjs.cloud.learning.auth.biz.sso.bean.SsoClientResponse;
import com.yjs.cloud.learning.auth.biz.sso.entity.SsoClient;

/**
 * service
 *
 * @author Bill.lai
 * @since 2021/6/2
 */
public interface SsoClientService {

    /**
     * 获取客户端信息
     * @param clientId 客户端id
     * @return 客户端信息
     */
    SsoClient getByClientId(String clientId);

    /**
     * 创建客户端
     * @param ssoClientCreateRequest 请求
     * @return 客户端
     */
    SsoClientResponse create(SsoClientCreateRequest ssoClientCreateRequest);
}
