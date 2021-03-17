package com.yjs.cloud.learning.behavior.biz.favorites.bean;

import com.yjs.cloud.learning.behavior.biz.favorites.enums.FavoriteTopicType;
import com.yjs.cloud.learning.behavior.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回对象
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class FavoriteResponse extends BaseResponse {

    @ApiModelProperty(value = "主题id", example = "1")
    private Long topicId;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private FavoriteTopicType topicType;

    @ApiModelProperty(value = "用户ID")
    private Long memberId;

}
