package com.yjs.cloud.learning.usercenter.biz.dingtalk.service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetbyunionidRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiUserGetbyunionidResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.biz.dingtalk.bean.DingTalkGetUserByCodeRequest;
import com.yjs.cloud.learning.usercenter.biz.dingtalk.config.DingTalkConfig;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 钉钉用户服务实现
 *
 * @author Bill.lai
 * @since 1/22/21
 */
@AllArgsConstructor
@Slf4j
@Service
public class DingTalkUserServiceImpl implements DingTalkUserService {

    private final DingTalkConfig dingTalkConfig;

    /**
     * 获取授权用户的个人信息
     * @param dingTalkGetUserByCodeRequest 请求参数
     * @return 用户信息
     */
    @Override
    public UserResponse getByCode(DingTalkGetUserByCodeRequest dingTalkGetUserByCodeRequest) {
        try {
            // 获取access_token
            String accessToken = DingTalkTokenService.getToken();
            // 通过临时授权码获取授权用户的个人信息
            DefaultDingTalkClient client2 = new DefaultDingTalkClient(dingTalkConfig.getApiHost() + "/sns/getuserinfo_bycode");
            OapiSnsGetuserinfoBycodeRequest getUserInfoByCodeRequest = new OapiSnsGetuserinfoBycodeRequest();
            // 通过扫描二维码，跳转指定的redirect_uri后，向url中追加的code临时授权码
            getUserInfoByCodeRequest.setTmpAuthCode(dingTalkGetUserByCodeRequest.getCode());
            OapiSnsGetuserinfoBycodeResponse byCodeResponse = client2.execute(getUserInfoByCodeRequest, dingTalkConfig.getLoginAppId(), dingTalkConfig.getLoginAppSecret());
            if (byCodeResponse.getUserInfo() == null || byCodeResponse.getUserInfo().getUnionid() == null) {
                throw new GlobalException("code无效");
            }
            // 根据unionId获取userId
            String unionId = byCodeResponse.getUserInfo().getUnionid();
            DingTalkClient clientDingTalkClient = new DefaultDingTalkClient(dingTalkConfig.getApiHost() + "/topapi/user/getbyunionid");
            OapiUserGetbyunionidRequest reqGetByUnionIdRequest = new OapiUserGetbyunionidRequest();
            reqGetByUnionIdRequest.setUnionid(unionId);
            OapiUserGetbyunionidResponse oapiUserGetbyunionidResponse = clientDingTalkClient.execute(reqGetByUnionIdRequest, accessToken);
            if (oapiUserGetbyunionidResponse.getResult() == null || oapiUserGetbyunionidResponse.getResult().getUserid() == null) {
                log.error("钉钉根据unionId获取userId失败，{}", oapiUserGetbyunionidResponse);
                throw new GlobalException(oapiUserGetbyunionidResponse.getSubMessage());
            }
            // 根据userId获取用户信息
            String userId = oapiUserGetbyunionidResponse.getResult().getUserid();
            DingTalkClient clientDingTalkClient2 = new DefaultDingTalkClient(dingTalkConfig.getApiHost() + "/topapi/v2/user/get");
            OapiV2UserGetRequest reqGetRequest = new OapiV2UserGetRequest();
            reqGetRequest.setUserid(userId);
            reqGetRequest.setLanguage("zh_CN");
            OapiV2UserGetResponse rspGetResponse = clientDingTalkClient2.execute(reqGetRequest, accessToken);
            System.out.println(rspGetResponse.getBody());
            if (rspGetResponse.getResult() == null) {
                log.error("钉钉根据userId获取用户信息失败，{}", rspGetResponse);
                throw new GlobalException("用户不存在");
            }
            OapiV2UserGetResponse.UserGetResponse userInfo = rspGetResponse.getResult();
            UserResponse userResponse = new UserResponse();
            userResponse.setMobile(userInfo.getMobile());
            return userResponse;
        } catch (Exception e) {
            log.error("获取钉钉授权用户的个人信息出错，授权码：{}", dingTalkGetUserByCodeRequest.getCode(), e);
            throw new GlobalException(e.getMessage());
        }
    }

}
