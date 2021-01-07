package com.yjs.cloud.learning.learn.biz.category.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 目录管理员端列表请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@ApiModel
@Data
public class CategoryAdminListRequest {

    @ApiModelProperty(value = "目录id，默认全部(0)", example = "0")
    private Long id = 0L;

    @ApiModelProperty(value = "查询所有子孙节点数据，默认false", example = "false")
    private Boolean fetchAll = false;

}
