package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonChapter;
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
public class LessonChapterCreateRequest {

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "短语介绍", required = true)
    private String phrase;

    @ApiModelProperty(value = "课程id", required = true)
    private Long lessonId;

    public LessonChapter convert() {
        LessonChapter entity = new LessonChapter();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
