package com.yjs.cloud.learning.learn.biz.record.bean;

import com.yjs.cloud.learning.learn.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 报名
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class RecordResponse extends BaseResponse {

    @ApiModelProperty(value = "课程ID")
    private Long lessonId;

    @ApiModelProperty(value = "课程章节ID")
    private Long lessonChapterSectionId;

    @ApiModelProperty(value = "报名ID")
    private Long signUpId;

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "学习时长")
    private Long learnTime;

}
