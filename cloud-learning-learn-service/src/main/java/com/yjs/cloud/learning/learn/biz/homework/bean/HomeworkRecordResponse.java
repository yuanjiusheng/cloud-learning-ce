package com.yjs.cloud.learning.learn.biz.homework.bean;

import com.yjs.cloud.learning.learn.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.learn.biz.homework.enums.HomeworkRecordStatus;
import com.yjs.cloud.learning.learn.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 作业记录
 *
 * @author Bill.lai
 * @since 2021/4/28
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
public class HomeworkRecordResponse extends BaseResponse {

    @ApiModelProperty(value = "课程Id")
    private Long lessonId;

    @ApiModelProperty(value = "会员Id")
    private Long memberId;

    @ApiModelProperty(value = "提交内容路径")
    private String url;

    @ApiModelProperty(value = "状态")
    private HomeworkRecordStatus status;

    @ApiModelProperty(value = "报名id")
    private Long signUpId;

    @ApiModelProperty(value = "会员信息")
    private MemberResponse member;

}
