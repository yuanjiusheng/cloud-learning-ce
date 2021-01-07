package com.yjs.cloud.learning.learn.biz.lesson.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 直播频道
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@ApiModel
@Data
public class LessonChapterGetListResponse {

    @ApiModelProperty(value = "课堂列表")
    private List<LessonChapterResponse> list;

}
