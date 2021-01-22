package com.yjs.cloud.learning.usercenter.biz.dingtalk.web;

import com.alibaba.fastjson.JSONObject;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.biz.dingtalk.bean.DingTalkGetUserByCodeRequest;
import com.yjs.cloud.learning.usercenter.biz.dingtalk.config.DingTalkConfig;
import com.yjs.cloud.learning.usercenter.biz.dingtalk.service.DingTalkUserService;
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
 * 钉钉控制器
 *
 * @author Bill.lai
 * @since 2020/5/29
 */
@Slf4j
@Api(tags = "钉钉")
@AllArgsConstructor
@RestController
public class DingTalkController {

    private final DingTalkConfig dingTalkConfig;
    private final DingTalkUserService dingTalkUserService;

    @ApiOperation(value = "获取钉钉配置信息", notes = "获取钉钉的相关配置信息，生成登录二维码时使用，如appId", httpMethod = "GET")
    @GetMapping("/public-api/ding-talk/config")
    public Map<String, Object> getWorkWeChatConfig() {
        Map<String, Object> result = new HashMap<>(4);
        result.put("appId", dingTalkConfig.getLoginAppId());
        result.put("token", dingTalkConfig.getToken());
        return result;
    }

    @ApiOperation(value = "根据CODE获取企业微信用户信息", notes = "根据CODE获取企业微信用户信息", httpMethod = "GET")
    @GetMapping("/public-api/ding-talk/user/by-code")
    public UserResponse getUserByCode(DingTalkGetUserByCodeRequest dingTalkGetUserByCodeRequest) {
        if (StringUtils.isEmpty(dingTalkGetUserByCodeRequest.getCode())) {
            throw new GlobalException("code为必填项");
        }
        return dingTalkUserService.getByCode(dingTalkGetUserByCodeRequest);
    }

}
