package com.yjs.cloud.learning.behavior.biz.favorites.bean;

import com.yjs.cloud.learning.behavior.biz.favorites.enums.FavoriteTopicType;
import com.yjs.cloud.learning.behavior.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class FavoriteGetPageListRequest extends PageRequest {

    @ApiModelProperty(value = "主题id")
    private List<Long> topicIdList;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private FavoriteTopicType topicType;

    @ApiModelProperty(value = "用户ID")
    private Long memberId;

}
