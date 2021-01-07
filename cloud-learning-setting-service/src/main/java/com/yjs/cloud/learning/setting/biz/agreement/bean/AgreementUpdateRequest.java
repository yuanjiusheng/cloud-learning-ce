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
public class AgreementUpdateRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "文章类型，自定义")
    private String type;

    @ApiModelProperty(value = "协议内容")
    private String content;

}
