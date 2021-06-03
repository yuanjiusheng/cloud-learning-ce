package com.yjs.cloud.learning.auth.biz.sso.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.auth.biz.sso.bean.SsoClientCreateRequest;
import com.yjs.cloud.learning.auth.biz.sso.bean.SsoClientResponse;
import com.yjs.cloud.learning.auth.biz.sso.entity.SsoClient;
import com.yjs.cloud.learning.auth.biz.sso.mapper.SsoClientMapper;
import com.yjs.cloud.learning.auth.common.service.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * service impl
 *
 * @author Bill.lai
 * @since 2021/6/2
 */
@Slf4j
@Service
public class SsoClientServiceImpl extends BaseServiceImpl<SsoClientMapper, SsoClient> implements SsoClientService {

    @Override
    public SsoClient getByClientId(String clientId) {
        LambdaQueryWrapper<SsoClient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SsoClient::getClientId, clientId);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public SsoClientResponse create(SsoClientCreateRequest ssoClientCreateRequest) {
        SsoClient ssoClient = new SsoClient();
        ssoClient.setClientName(ssoClientCreateRequest.getClientName());
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        ssoClient.setClientId(uuid);
        ssoClient.setClientSecret(base64Encode(md5(uuid)));
        save(ssoClient);
        return ssoClient.convert();
    }

    private String base64Encode(String content){
        BASE64Encoder baseEncode = new BASE64Encoder();
        return baseEncode.encode(content.getBytes());
    }

    private String md5(String content) {
        StringBuilder sb = new StringBuilder();
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = content.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7' , '8', '9', 'a', 'b', 'c', 'd', 'e','f'};
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
        } catch (Exception e) {
            return content;
        }
        return sb.toString();
    }
}
