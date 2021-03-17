package com.yjs.cloud.learning.behavior.biz.favorites.bean;

import com.yjs.cloud.learning.behavior.biz.favorites.entity.Favorite;
import com.yjs.cloud.learning.behavior.biz.favorites.enums.FavoriteTopicType;
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
public class FavoriteCreateRequest {

    @ApiModelProperty(value = "主题id", example = "1")
    private Long topicId;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private FavoriteTopicType topicType;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long memberId;

    public Favorite convert() {
        Favorite entity = new Favorite();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
