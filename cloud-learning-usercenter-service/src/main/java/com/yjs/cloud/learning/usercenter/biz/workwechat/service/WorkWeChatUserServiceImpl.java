package com.yjs.cloud.learning.usercenter.biz.workwechat.service;

import com.alibaba.fastjson.JSONObject;
import com.yjs.cloud.learning.usercenter.common.util.StringUtils;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信用户服务实现
 *
 * @author Bill.lai
 * @since 2020/5/29
 */
@AllArgsConstructor
@Service
public class WorkWeChatUserServiceImpl implements WorkWeChatUserService {

    private final WorkWeChatRequestService workWeChatRequestService;

    /**
     * 获取用户信息
     * @param code 通过成员授权获取到的code，code只能使用一次，5分钟未被使用自动过期。
     * @return 用户信息
     */
    @Override
    public JSONObject getByCode(String code) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("code", code);
        JSONObject response = workWeChatRequestService.get("/user/getuserinfo", params);
        if (!CollectionUtils.isEmpty(response)) {
            String userId = response.getString("UserId");
            // 企业内部员工返回UserId， 非企业员工返回OpenId
            if (StringUtils.isEmpty(userId)) {
                throw new GlobalException("用户没有权限");
            }
        }
        return response;
    }

    /**
     * 获取用户信息
     * @param userId 企业微信用户id
     * @return 用户信息
     */
    @Override
    public JSONObject getById(String userId) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("userid", userId);
        JSONObject user = workWeChatRequestService.get("/user/get", params);
        if (CollectionUtils.isEmpty(user)) {
            throw new GlobalException("用户信息为空");
        }
        return user;
    }

}
