package com.yjs.cloud.learning.behavior.biz.favorites.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.behavior.biz.favorites.bean.FavoriteResponse;
import com.yjs.cloud.learning.behavior.biz.favorites.enums.FavoriteTopicType;
import com.yjs.cloud.learning.behavior.common.entity.BaseEntity;
import com.yjs.cloud.learning.behavior.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 收藏
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_favorite")
public class Favorite extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主题ID
     */
    private Long topicId;

    /**
     * 主题类型
     */
    private FavoriteTopicType topicType;

    /**
     * 用户id
     */
    private Long memberId;

    public FavoriteResponse convert() {
        FavoriteResponse bean = new FavoriteResponse();
        BeanCopierUtils.copy(this, bean);
        return bean;
    }
}
