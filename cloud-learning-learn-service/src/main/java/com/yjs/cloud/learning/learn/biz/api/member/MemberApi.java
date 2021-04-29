package com.yjs.cloud.learning.learn.biz.api.member;

import com.yjs.cloud.learning.learn.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.learn.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    public List<MemberResponse> getByIds(List<Long> ids) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("ids", ids);
        return requestService.list("/member/public-api/by-ids", param, MemberResponse.class);
    }

    public Map<Long, MemberResponse> getMemberMap(List<Long> memberIdList) {
        // 查询会员信息
        List<MemberResponse> memberList = getByIds(memberIdList);
        Map<Long, MemberResponse> memberMap = new HashMap<>(16);
        for (MemberResponse memberResponse : memberList) {
            memberMap.put(memberResponse.getId(), memberResponse);
        }
        return memberMap;
    }

}
