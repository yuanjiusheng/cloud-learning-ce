package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonChapterSection;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
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
public class LessonChapterSectionCreateRequest {

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "视频地址", required = true)
    private String url;

    @ApiModelProperty(value = "短语介绍", required = true)
    private String phrase;

    @ApiModelProperty(value = "章节id", required = true)
    private Long lessonChapterId;

    @ApiModelProperty(value = "视频时长", required = true)
    private Long totalTime;

    public LessonChapterSection convert() {
        LessonChapterSection entity = new LessonChapterSection();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
