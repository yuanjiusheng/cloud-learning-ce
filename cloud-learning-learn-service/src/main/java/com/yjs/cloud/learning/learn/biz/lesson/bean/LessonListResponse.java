package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 课程列表
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class LessonListResponse extends PageResponse {

    @ApiModelProperty(value = "课堂列表")
    private List<LessonResponse> list;

}
