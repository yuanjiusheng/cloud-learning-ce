package com.yjs.cloud.learning.learn.biz.homework.bean;

import com.yjs.cloud.learning.learn.biz.homework.enums.HomeworkRecordStatus;
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
public class HomeworkRecordApprovalRequest {

    @ApiModelProperty(value = "课程Id")
    private Long lessonId;

    @ApiModelProperty(value = "会员Id")
    private Long memberId;

    @ApiModelProperty(value = "报名id")
    private Long signUpId;

    @ApiModelProperty(value = "状态", hidden = true)
    private HomeworkRecordStatus status;

}
