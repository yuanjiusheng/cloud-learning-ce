package com.yjs.cloud.learning.learn.biz.homework.bean;

import com.yjs.cloud.learning.learn.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2021/4/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class HomeworkRecordListRequest extends PageRequest {

    @ApiModelProperty(value = "课程Id")
    private Long lessonId;

}
