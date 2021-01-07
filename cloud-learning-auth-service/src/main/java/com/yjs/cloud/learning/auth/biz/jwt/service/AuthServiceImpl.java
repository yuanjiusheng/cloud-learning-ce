package com.yjs.cloud.learning.auth.biz.jwt.service;

import org.springframework.stereotype.Service;

/**
 * auth service impl
 *
 * @author Andrew.xiao
 * @since 2020/7/22
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String getAuthCode(String mobilePhone) {
        return "123456";
    }

}
