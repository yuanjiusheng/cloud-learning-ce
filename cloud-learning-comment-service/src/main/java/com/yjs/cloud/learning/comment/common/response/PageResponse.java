package com.yjs.cloud.learning.comment.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Page Base返回对象
 *
 * @author Bill.lai
 * @since 2020/9/25
 */
@Data
public class PageResponse {

    @ApiModelProperty(value = "当前页")
    private Long current;

    @ApiModelProperty(value = "页数量")
    private Long size;

    @ApiModelProperty(value = "总条数")
    private Long total;

    @ApiModelProperty(value = "分页页码数量")
    private Long pages;
}
