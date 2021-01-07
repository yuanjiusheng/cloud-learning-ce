package com.yjs.cloud.learning.auth.biz.api.user;

import com.yjs.cloud.learning.auth.biz.api.user.bean.UserResponse;
import com.yjs.cloud.learning.auth.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户API
 *
 * @author Bill.lai
 * @since 12/31/20
 */
@AllArgsConstructor
@Service
public class UserApi {

    private final RequestService requestService;

    public UserResponse getByMobile(String mobile) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("mobile", mobile);
        return requestService.get("/user-center/auth-api/by-mobile", param, UserResponse.class);
    }

}
