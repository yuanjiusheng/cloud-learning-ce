package com.yjs.cloud.learning.setting.biz.agreement.bean;

import com.yjs.cloud.learning.setting.common.response.BaseResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 协议文章
 *
 * @author Bill.lai
 * @since 12/29/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AgreementResponse extends BaseResponse {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "文章类型，自定义")
    private String type;

    @ApiModelProperty(value = "协议内容")
    private String content;

}
