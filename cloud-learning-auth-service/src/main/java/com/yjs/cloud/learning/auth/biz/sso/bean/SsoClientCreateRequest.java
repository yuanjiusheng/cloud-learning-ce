package com.yjs.cloud.learning.auth.biz.sso.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Bill.lai
 * @since 2021/6/2
 */
@Data
@ApiModel
public class SsoClientCreateRequest {

    @ApiModelProperty(value = "名称")
    private String clientName;

}
