package com.yjs.cloud.learning.learn.biz.api.favorite.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@ApiModel
@Data
public class FavoriteCountResponse {

    @ApiModelProperty(value = "主题id")
    private Long topicId;

    @ApiModelProperty(value = "数量")
    private Long num;

}
