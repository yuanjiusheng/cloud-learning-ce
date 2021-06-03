package com.yjs.cloud.learning.auth.biz.sso.bean;

import com.yjs.cloud.learning.auth.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Bill.lai
 * @since 2021/6/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class SsoClientResponse extends BaseResponse {

    @ApiModelProperty(value = "客户端名称")
    private String clientName;

    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    @ApiModelProperty(value = "客户端秘钥")
    private String clientSecret;

}
