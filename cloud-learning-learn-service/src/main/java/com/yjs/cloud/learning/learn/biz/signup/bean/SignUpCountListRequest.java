package com.yjs.cloud.learning.learn.biz.signup.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 学习记录
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@ApiModel
@Data
public class SignUpCountListRequest {

    @ApiModelProperty(value = "课程id列表")
    private List<Long> lessonIdList;

}
