package com.yjs.cloud.learning.usercenter.biz.workwechat.web;

import com.alibaba.fastjson.JSONObject;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.biz.workwechat.bean.WorkWeChatGetUserByCodeRequest;
import com.yjs.cloud.learning.usercenter.biz.workwechat.config.WorkWeChatConfig;
import com.yjs.cloud.learning.usercenter.biz.workwechat.service.WorkWeChatTokenService;
import com.yjs.cloud.learning.usercenter.biz.workwechat.service.WorkWeChatUserService;
import com.yjs.cloud.learning.usercenter.common.util.StringUtils;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 *
 * @author Bill.lai
 * @since 2020/5/29
 */
@Slf4j
@Api(tags = "企业微信登录控制器")
@AllArgsConstructor
@RestController
public class WorkWeChatController {

    private final WorkWeChatConfig workWeChatConfig;
    private final WorkWeChatTokenService workWeChatTokenService;
    private final WorkWeChatUserService workWeChatUserService;

    @ApiOperation(value = "获取企业微信token", notes = "获取/更新企业微信token", httpMethod = "GET")
    @GetMapping("/work-we-chat/token")
    public Map<String, Object> token() {
        return workWeChatTokenService.getAccessToken();
    }

    @ApiOperation(value = "获取企业微信配置信息", notes = "获取企业微信的相关配置信息，生成登录二维码时使用，如appId/agentId/redirectUri/state", httpMethod = "GET")
    @GetMapping("/public-api/work-we-chat/config")
    public Map<String, Object> getWorkWeChatConfig() {
        Map<String, Object> result = new HashMap<>(4);
        result.put("appId", workWeChatConfig.getCorpId());
        result.put("agentId", workWeChatConfig.getAgentId());
        result.put("state", workWeChatConfig.getState());
        return result;
    }

    @ApiOperation(value = "根据CODE获取企业微信用户信息", notes = "根据CODE获取企业微信用户信息", httpMethod = "GET")
    @GetMapping("/public-api/work-we-chat/user/by-code")
    public UserResponse getUserByCode(WorkWeChatGetUserByCodeRequest workWeChatGetUserByCodeRequest) {
        if (StringUtils.isEmpty(workWeChatGetUserByCodeRequest.getCode())) {
            throw new GlobalException("code为必填项");
        }
        JSONObject response = workWeChatUserService.getByCode(workWeChatGetUserByCodeRequest.getCode());
        if (CollectionUtils.isEmpty(response)) {
            throw new GlobalException("用户不存在");
        }
        // 获取企业微信用户信息
        JSONObject userJson = workWeChatUserService.getById(response.getString("UserId"));
        if (CollectionUtils.isEmpty(userJson)) {
            throw new GlobalException("用户不存在");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setMobile(userJson.getString("mobile"));
        return userResponse;
    }

}
