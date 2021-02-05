package com.yjs.cloud.learning.comment.biz.sensitiveword.bean;

import com.yjs.cloud.learning.comment.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2/3/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class WordGetListRequest extends PageRequest {

    @ApiModelProperty(value = "关键字")
    private String keyword;

}
