package com.yjs.cloud.learning.auth.biz.api.user;

import com.yjs.cloud.learning.auth.biz.api.user.bean.UserResponse;
import com.yjs.cloud.learning.auth.common.request.service.RequestService;
import com.yjs.cloud.learning.auth.common.web.GlobalException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户API
 *
 * @author Bill.lai
 * @since 12/31/20
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserApi {

    private final RequestService requestService;

    public UserResponse getByMobile(String mobile) {
        try {
            Map<String, Object> param = new HashMap<>(1);
            param.put("mobile", mobile);
            return requestService.get("/user-center/auth-api/by-mobile", param, UserResponse.class);
        } catch (Exception e) {
            log.error("根据手机号获取用户出错，手机号码：{}", mobile, e);
            throw new GlobalException(e.getMessage());
        }
    }

    public UserResponse getByWorkWeChatCode(String code) {
        try {
            Map<String, Object> param = new HashMap<>(1);
            param.put("code", code);
            return requestService.get("/user-center/public-api/work-we-chat/user/by-code", param, UserResponse.class);
        } catch (Exception e) {
            log.error("获取企业微信用户信息出错，验证码：{}", code, e);
            throw new GlobalException(e.getMessage());
        }
    }

    public UserResponse getByDingTalkCode(String code) {
        try {
            Map<String, Object> param = new HashMap<>(1);
            param.put("code", code);
            return requestService.get("/user-center/public-api/ding-talk/user/by-code", param, UserResponse.class);
        } catch (Exception e) {
            log.error("获取钉钉用户信息出错，验证码：{}", code, e);
            throw new GlobalException(e.getMessage());
        }
    }

}
