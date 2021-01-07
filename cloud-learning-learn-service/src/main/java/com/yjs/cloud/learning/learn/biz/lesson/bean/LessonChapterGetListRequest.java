package com.yjs.cloud.learning.learn.biz.lesson.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class LessonChapterGetListRequest {

    @ApiModelProperty(value = "课程id", required = true)
    private Long lessonId;

}
