package com.yjs.cloud.learning.learn.biz.record.bean;

import com.yjs.cloud.learning.learn.biz.record.entity.Record;
import com.yjs.cloud.learning.learn.biz.record.enums.RecordStatus;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
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
public class RecordCreateRequest {

    @ApiModelProperty(value = "课程ID")
    private Long lessonId;

    @ApiModelProperty(value = "课程章节ID")
    private Long lessonChapterSectionId;

    @ApiModelProperty(value = "会员ID", hidden = true)
    private Long memberId;

    @ApiModelProperty(value = "学习时长")
    private Long learnTime;

    @ApiModelProperty(value = "报名ID")
    private Long signUpId;

    @ApiModelProperty(value = "最大学习进度时间")
    private Long maxProgressTime;

    @ApiModelProperty(value = "状态", hidden = true)
    private RecordStatus status = RecordStatus.progressing;

    public Record convert() {
        Record entity = new Record();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
