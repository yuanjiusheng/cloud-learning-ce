package com.yjs.cloud.learning.learn.biz.homework.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2021/4/28
 */
@ApiModel
@Data
public class HomeworkRecordGetRequest {

    @ApiModelProperty(value = "课程Id")
    private Long lessonId;

    @ApiModelProperty(value = "会员Id", hidden = true)
    private Long memberId;

    @ApiModelProperty(value = "报名id")
    private Long signUpId;

}
