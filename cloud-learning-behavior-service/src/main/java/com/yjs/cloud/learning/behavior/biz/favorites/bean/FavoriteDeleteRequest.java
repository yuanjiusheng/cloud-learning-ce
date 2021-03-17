package com.yjs.cloud.learning.behavior.biz.favorites.bean;

import com.yjs.cloud.learning.behavior.biz.favorites.enums.FavoriteTopicType;
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
public class FavoriteDeleteRequest {

    @ApiModelProperty(value = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "主题id", example = "1")
    private Long topicId;

    @ApiModelProperty(value = "主题类型（主题ID不为空时必填）")
    private FavoriteTopicType topicType;

}
