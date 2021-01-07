package com.yjs.cloud.learning.learn.biz.record.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 学习记录
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@ApiModel
@Data
public class RecordUpdateRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "课程id")
    private Long lessonId;

    @ApiModelProperty(value = "课程章节id")
    private Long lessonChapterSectionId;

    @ApiModelProperty(value = "会员ID", hidden = true)
    private Long memberId;

    @ApiModelProperty(value = "学习时长")
    private Long learnTime;

    @ApiModelProperty(value = "报名ID")
    private Long signUpId;

}
