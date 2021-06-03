package com.yjs.cloud.learning.auth.biz.sso.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.auth.biz.sso.bean.SsoClientResponse;
import com.yjs.cloud.learning.auth.common.entity.BaseEntity;
import com.yjs.cloud.learning.auth.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 单点登录客户端
 *
 * @author Bill.lai
 * @since 2021/6/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_sso_client")
public class SsoClient extends BaseEntity {

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * 客户端名称
     */
    private String clientName;

    public SsoClientResponse convert() {
        SsoClientResponse response = new SsoClientResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }
}
