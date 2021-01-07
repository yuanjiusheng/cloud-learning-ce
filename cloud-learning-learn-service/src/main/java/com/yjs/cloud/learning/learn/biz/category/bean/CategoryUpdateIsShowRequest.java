package com.yjs.cloud.learning.learn.biz.category.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 目录是否显示请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@ApiModel
@Data
public class CategoryUpdateIsShowRequest {

    @ApiModelProperty(value = "类目id", required = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "显示=true/隐藏=false", required = true, example = "false")
    private Boolean isShow;

}
