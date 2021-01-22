package com.yjs.cloud.learning.auth.biz.jwt.web;

import com.yjs.cloud.learning.auth.biz.jwt.bean.AuthGetAuthCodeRequest;
import com.yjs.cloud.learning.auth.biz.jwt.service.AuthCodeStoreService;
import com.yjs.cloud.learning.auth.biz.jwt.service.AuthService;
import com.yjs.cloud.learning.auth.common.util.StringUtils;
import com.yjs.cloud.learning.auth.common.web.GlobalException;
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
     * @param authGetAuthCodeRequest 手机号码
     */
    @ApiOperation(value = "获取验证码", notes = "获取验证码", httpMethod = "GET")
    @GetMapping("/public-api/auth-code")
    public void getAuthCode(AuthGetAuthCodeRequest authGetAuthCodeRequest) {
        if (StringUtils.isEmpty(authGetAuthCodeRequest.getMobile())) {
            throw new GlobalException("mobile为必填项");
        }
        // 获取验证码
        String code = authService.getAuthCode(authGetAuthCodeRequest.getMobile());
        // TODO 发送短信

        // 保存在缓存里
        authCodeStoreService.save(authGetAuthCodeRequest.getMobile(), code);
    }

}
