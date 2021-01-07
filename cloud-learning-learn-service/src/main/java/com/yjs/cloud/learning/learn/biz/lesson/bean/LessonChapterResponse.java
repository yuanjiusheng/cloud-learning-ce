package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class LessonChapterResponse extends BaseResponse {

    @ApiModelProperty(value = "课程id", example = "1")
    private Long lessonId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "简介")
    private String phrase;

    @ApiModelProperty(value = "章节内容")
    public List<LessonChapterSectionResponse> chapterSectionList;

}
