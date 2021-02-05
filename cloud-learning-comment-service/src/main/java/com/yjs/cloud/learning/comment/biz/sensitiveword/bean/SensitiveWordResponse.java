package com.yjs.cloud.learning.comment.biz.sensitiveword.bean;

import com.yjs.cloud.learning.comment.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Bill.lai
 * @since 2/3/21
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class SensitiveWordResponse extends BaseResponse {

    @ApiModelProperty(value = "名称")
    private String name;

}
