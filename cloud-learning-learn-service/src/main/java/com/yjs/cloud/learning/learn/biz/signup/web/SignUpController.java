package com.yjs.cloud.learning.learn.biz.signup.web;

import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpCreateRequest;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpDeleteRequest;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpGetRequest;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpResponse;
import com.yjs.cloud.learning.learn.biz.signup.service.SignUpService;
import com.yjs.cloud.learning.learn.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 报名控制器
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@Api(tags = "报名")
@AllArgsConstructor
@RestController
public class SignUpController extends BaseController {

    private final SignUpService signUpService;

    @ApiOperation(value = "报名", notes = "报名", httpMethod = "POST")
    @PostMapping(value = "/auth-api/sign-up")
    public SignUpResponse create(@RequestBody SignUpCreateRequest signUpCreateRequest) {
        signUpCreateRequest.setMemberId(getLoginUserId());
        return signUpService.create(signUpCreateRequest);
    }

    @ApiOperation(value = "取消报名", notes = "取消报名", httpMethod = "DELETE")
    @DeleteMapping(value = "/auth-api/sign-up")
    public void cancel(@RequestBody SignUpDeleteRequest signUpDeleteRequest) {
        signUpDeleteRequest.setMemberId(getLoginUserId());
        signUpService.cancel(signUpDeleteRequest);
    }

    @ApiOperation(value = "获取报名信息", notes = "获取报名信息", httpMethod = "GET")
    @GetMapping(value = "/public-api/sign-up")
    public SignUpResponse get(SignUpGetRequest signUpGetRequest) {
        try{
            getLoginUserId();
        }catch (Exception e) {
            return null;
        }
        return signUpService.get(signUpGetRequest);
    }

}
