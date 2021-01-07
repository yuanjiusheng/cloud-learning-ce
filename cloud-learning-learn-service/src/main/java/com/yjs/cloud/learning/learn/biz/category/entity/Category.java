package com.yjs.cloud.learning.learn.biz.category.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.biz.category.bean.CategoryResponse;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品类目
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_category")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    private Integer sortOrder;

    /**
     * 是否显示
     */
    private Boolean isShow;

    /**
     * 是否在首页显示
     */
    private Boolean isShowIndex;

    /**
     * 分类图片
     */
    private String image;

    /**
     * 类目级别
     */
    private Integer level;

    public CategoryResponse convert() {
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanCopierUtils.copy(this, categoryResponse);
        return categoryResponse;
    }
}
