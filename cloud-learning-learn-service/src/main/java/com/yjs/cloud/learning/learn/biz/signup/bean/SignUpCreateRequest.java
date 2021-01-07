package com.yjs.cloud.learning.learn.biz.signup.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 报名
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@ApiModel
@Data
public class SignUpCreateRequest {

    @ApiModelProperty(value = "课程id")
    private Long lessonId;

    @ApiModelProperty(value = "会员ID", hidden = true)
    private Long memberId;

}
