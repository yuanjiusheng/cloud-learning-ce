package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.biz.lesson.entity.Lesson;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程更新请求
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@ApiModel
@Data
public class LessonUpdateRequest {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "课程名称（最大长度64个字符，只支持中文、字母、数字和下划线）", required = true)
    private String name;

    @ApiModelProperty(value = "编号", required = true)
    private String code;

    @ApiModelProperty(value = "开始时间", required = true)
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    private LocalDateTime endTime;

    @ApiModelProperty(value = "封面图片（海报、banner）", required = true)
    private String image;

    @ApiModelProperty(value = "是否可见", required = true)
    private Boolean isShow;

    @ApiModelProperty(value = "状态", required = true)
    private String status;

    @ApiModelProperty(value = "短语介绍", required = true)
    private String phrase;

    @ApiModelProperty(value = "活动描述", required = true)
    private String introduction;

    @ApiModelProperty(value = "分类id列表", required = true)
    private List<Long> cIdList;

    public Lesson convert() {
        Lesson entity = new Lesson();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }
}
