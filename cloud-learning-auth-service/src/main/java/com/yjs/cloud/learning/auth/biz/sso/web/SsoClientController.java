package com.yjs.cloud.learning.auth.biz.sso.web;

import com.yjs.cloud.learning.auth.biz.sso.bean.SsoClientCreateRequest;
import com.yjs.cloud.learning.auth.biz.sso.bean.SsoClientResponse;
import com.yjs.cloud.learning.auth.biz.sso.service.SsoClientService;
import com.yjs.cloud.learning.auth.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bill.lai
 * @since 2021/6/2
 */
@Api(tags = "SSO客户端")
@AllArgsConstructor
@RestController
public class SsoClientController extends BaseController {

    private final SsoClientService ssoClientService;

    @ApiOperation(value = "创建客户端", httpMethod = "POST")
    @PostMapping(value = "/sso/client")
    public SsoClientResponse create(@RequestBody SsoClientCreateRequest ssoClientCreateRequest) {
        return ssoClientService.create(ssoClientCreateRequest);
    }

}
