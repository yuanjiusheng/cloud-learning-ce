package com.yjs.cloud.learning.learn.biz.signup.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Bill.lai
 * @since 12/16/20
 */
@ApiModel
@Data
public class SignUpCountResponse {

    @ApiModelProperty(value = "课程id")
    private Long lessonId;

    @ApiModelProperty(value = "数量")
    private Long num;

}
