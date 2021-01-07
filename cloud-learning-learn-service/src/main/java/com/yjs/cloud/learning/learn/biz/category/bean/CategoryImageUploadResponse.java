package com.yjs.cloud.learning.learn.biz.category.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 目录图片上传返回参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@ApiModel
@Data
public class CategoryImageUploadResponse {

    @ApiModelProperty(value = "图片路径")
    private String url;

}
