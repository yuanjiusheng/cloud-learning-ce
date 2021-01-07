package com.yjs.cloud.learning.learn.biz.lesson.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 删除课程章节请求
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@ApiModel
@Data
public class LessonChapterDeleteRequest {

    @ApiModelProperty(value = "ID", required = true)
    private Long id;

}
