package com.yjs.cloud.learning.behavior.biz.like.bean;

import com.yjs.cloud.learning.behavior.biz.like.entity.Like;
import com.yjs.cloud.learning.behavior.biz.like.enums.LikeTopicType;
import com.yjs.cloud.learning.behavior.common.util.BeanCopierUtils;
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
public class LikeCreateRequest {

    @ApiModelProperty(value = "主题id", example = "1")
    private Long topicId;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private LikeTopicType topicType;

    @ApiModelProperty(value = "点赞状态", example = "true")
    private Boolean status;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long memberId;

    public Like convert() {
        Like entity = new Like();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
