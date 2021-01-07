package com.yjs.cloud.learning.auth.biz.jwt.web;

import com.yjs.cloud.learning.auth.biz.jwt.service.AuthCodeStoreService;
import com.yjs.cloud.learning.auth.biz.jwt.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auth 控制器
 *
 * @author Andrew.xiao
 * @since 2020/7/22
 */
@AllArgsConstructor
@Api(tags = "认证授权")
@RestController
@Slf4j
public class AuthController {

    private final AuthService authService;

    private final AuthCodeStoreService authCodeStoreService;

    /**
     * 获取验证码
     * @param mobilePhone 手机号码
     */
    @ApiOperation(value = "获取验证码", notes = "获取验证码", httpMethod = "GET")
    @GetMapping("/auth-code")
    public void getAuthCode(String mobilePhone) {
        String code = authService.getAuthCode(mobilePhone);
        authCodeStoreService.save(mobilePhone, code);
    }

}
