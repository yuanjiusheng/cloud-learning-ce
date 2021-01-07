package com.yjs.cloud.learning.auth.biz.api.member;

import com.yjs.cloud.learning.auth.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.auth.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 会员api
 *
 * @author Bill.lai
 * @since 12/31/20
 */
@Slf4j
@AllArgsConstructor
@Service
public class MemberApi {

    private final RequestService requestService;

    public MemberResponse getByMobile(String mobile) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("mobile", mobile);
        return requestService.get("/member/auth-api/by-mobile", param, MemberResponse.class);
    }

}
