package com.yjs.cloud.learning.learn.biz.category.service;

import com.yjs.cloud.learning.learn.biz.category.bean.CategoryCreateRequest;
import com.yjs.cloud.learning.learn.biz.category.bean.CategoryUpdateRequest;
import com.yjs.cloud.learning.learn.biz.category.entity.CategoryRelation;
import com.yjs.cloud.learning.learn.common.service.IBaseService;

import java.util.List;

/**
 * <p>
 * 商品类目关系 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
public interface CategoryRelationService extends IBaseService<CategoryRelation> {

    /**
     * 创建类目与类目的关系
     * @param categoryCreateRequest 商品类目参数
     */
    void create(CategoryCreateRequest categoryCreateRequest);

    /**
     * 根据父级id获取祖辈的列表
     * @param pid 父级id
     * @return 祖辈类目
     */
    List<Long> getParentList(Long pid);

    /**
     * 更新类目与类目的关系
     * @param categoryUpdateRequest 商品类目参数
     */
    void update(CategoryUpdateRequest categoryUpdateRequest);

    /**
     * 删除类目与类目的关系
     * @param childCategoryId 类目id
     */
    void deleteByChildCategoryId(Long childCategoryId);

    /**
     * 根据cid获取父类
     * @param categoryId cid
     * @return 父级类目
     */
    List<CategoryRelation> findParentByCid(Long categoryId);

    /**
     * 根据cid获取直属父类id
     * @param categoryId cid
     * @return 父级类目id
     */
    CategoryRelation findParentIdByCid(Long categoryId);
}
