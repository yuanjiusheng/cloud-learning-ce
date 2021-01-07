package com.yjs.cloud.learning.setting.biz.agreement.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 协议文章
 *
 * @author Bill.lai
 * @since 12/29/20
 */
@Data
@ApiModel
public class AgreementGetRequest {

    @ApiModelProperty(value = "文章类型，自定义")
    private String type;

}
