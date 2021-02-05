package com.yjs.cloud.learning.comment.biz.sensitiveword.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2/5/21
 */
@ApiModel
@Data
public class WordDeleteRequest {

    @ApiModelProperty(value = "id")
    private Long id;

}
