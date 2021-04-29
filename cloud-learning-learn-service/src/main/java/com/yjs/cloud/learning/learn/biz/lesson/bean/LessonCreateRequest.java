package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.biz.lesson.entity.Lesson;
import com.yjs.cloud.learning.learn.biz.lesson.enums.LessonStatus;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 创建课程请求
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@ApiModel
@Data
public class LessonCreateRequest {

    @ApiModelProperty(value = "课程名称", required = true)
    private String name;

    @ApiModelProperty(value = "编号", hidden = true)
    private String code;

    @ApiModelProperty(value = "开始时间", required = true)
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    private LocalDateTime endTime;

    @ApiModelProperty(value = "封面图片", required = true)
    private String image;

    @ApiModelProperty(value = "状态", required = true)
    private LessonStatus status = LessonStatus.unpublished;

    @ApiModelProperty(value = "短语介绍", required = true)
    private String phrase;

    @ApiModelProperty(value = "活动描述", required = true)
    private String introduction;

    @ApiModelProperty(value = "分类id列表", required = true)
    private List<Long> cidList;

    @ApiModelProperty(value = "作业内容")
    private String homework;

    @ApiModelProperty(value = "作业附件")
    private String homeworkAttachment;

    public Lesson convert() {
        Lesson entity = new Lesson();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
