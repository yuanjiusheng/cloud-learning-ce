package com.yjs.cloud.learning.learn.biz.category.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品类目关系
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_category_relation")
public class CategoryRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 子类目id
     */
    private Long childCategoryId;

    /**
     * 父类目id
     */
    private Long fatherCategoryId;

    /**
     * 直属父类目id
     */
    private Long directFatherCategoryId;

    /**
     * 是否属于子类目
     */
    private Integer isSub;

}
