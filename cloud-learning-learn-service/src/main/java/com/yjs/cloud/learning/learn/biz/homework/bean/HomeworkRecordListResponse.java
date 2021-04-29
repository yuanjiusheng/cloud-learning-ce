package com.yjs.cloud.learning.learn.biz.homework.bean;

import com.yjs.cloud.learning.learn.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 返回
 *
 * @author Bill.lai
 * @since 2021/4/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class HomeworkRecordListResponse extends PageResponse {

    @ApiModelProperty(value = "课程列表")
    private List<HomeworkRecordResponse> list;

}
