package com.yjs.cloud.learning.learn.biz.category.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 目录首页显示更新请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@ApiModel
@Data
public class CategoryUpdateIsShowIndexRequest {

    @ApiModelProperty(value = "类目id", required = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "首页显示=true/首页隐藏=false", required = true, example = "false")
    private Boolean isShowIndex;

}
