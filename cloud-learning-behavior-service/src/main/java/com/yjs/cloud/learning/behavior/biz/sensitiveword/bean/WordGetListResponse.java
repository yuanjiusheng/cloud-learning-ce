package com.yjs.cloud.learning.behavior.biz.sensitiveword.bean;

import com.yjs.cloud.learning.behavior.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 返回结果
 *
 * @author Bill.lai
 * @since 2/3/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class WordGetListResponse extends PageResponse {

    @ApiModelProperty(value = "列表")
    private List<SensitiveWordResponse> list;

}
