package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.biz.lesson.enums.LessonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 请求
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@ApiModel
@Data
public class LessonGetRequest {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "会员id", hidden = true)
    private Long memberId;

    @ApiModelProperty(value = "部门id", hidden = true)
    private Long departmentId;

    @ApiModelProperty(value = "状态", hidden = true)
    private LessonStatus status;

}
