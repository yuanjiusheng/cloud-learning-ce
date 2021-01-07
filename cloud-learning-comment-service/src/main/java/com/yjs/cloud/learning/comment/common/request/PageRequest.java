package com.yjs.cloud.learning.comment.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Page Base返回对象
 *
 * @author Bill.lai
 * @since 2020/9/25
 */
@Data
public class PageRequest {

    @ApiModelProperty(value = "当前页")
    private Long current = 1L;

    @ApiModelProperty(value = "页数量")
    private Long size = 20L;

}
