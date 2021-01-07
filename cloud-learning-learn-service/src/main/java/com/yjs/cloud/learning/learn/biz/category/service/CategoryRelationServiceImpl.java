package com.yjs.cloud.learning.learn.biz.category.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.learn.biz.category.bean.CategoryCreateRequest;
import com.yjs.cloud.learning.learn.biz.category.bean.CategoryUpdateRequest;
import com.yjs.cloud.learning.learn.biz.category.entity.CategoryRelation;
import com.yjs.cloud.learning.learn.biz.category.mapper.CategoryRelationMapper;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品类目关系 服务实现类
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@AllArgsConstructor
@Service
public class CategoryRelationServiceImpl extends BaseServiceImpl<CategoryRelationMapper, CategoryRelation> implements CategoryRelationService {

    /**
     * 创建类目关系
     * @param categoryCreateRequest 商品类目参数
     */
    @Override
    public void create(CategoryCreateRequest categoryCreateRequest) {
        List<Long> parentIdList = new ArrayList<>();
        Long pid = categoryCreateRequest.getPid();
        if (categoryCreateRequest.getPid() == null || categoryCreateRequest.getPid() == 0) {
            // 没有父级，默认最高等级
            parentIdList.add(0L);
            pid = 0L;
        } else {
            parentIdList = getParentList(pid);
        }
        List<CategoryRelation> categoryCategoryList = new ArrayList<>();
        CategoryRelation categoryCategory;
        for (Long cid : parentIdList) {
            categoryCategory = new CategoryRelation();
            categoryCategory.setChildCategoryId(categoryCreateRequest.getId());
            categoryCategory.setDirectFatherCategoryId(pid);
            categoryCategory.setFatherCategoryId(cid);
            categoryCategory.setIsSub(cid.equals(pid) ? 1 : 0);
            categoryCategoryList.add(categoryCategory);
        }
        saveBatch(categoryCategoryList);
    }

    /**
     * 更新类目关系
     * @param categoryUpdateRequest 参数
     */
    @Override
    public void update(CategoryUpdateRequest categoryUpdateRequest) {
        // 删除原来的类目关系
        deleteByChildCategoryId(categoryUpdateRequest.getId());
        CategoryCreateRequest categoryCreateRequest = new CategoryCreateRequest();
        BeanCopierUtils.copy(categoryUpdateRequest, categoryCreateRequest);
        // 创建新的类目关系
        create(categoryCreateRequest);
    }

    /**
     * 删除类目关系
     * @param childCategoryId 类目id
     */
    @Override
    public void deleteByChildCategoryId(Long childCategoryId) {
        // 获取所有父级
        LambdaQueryWrapper<CategoryRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CategoryRelation::getChildCategoryId, childCategoryId);
        remove(lambdaQueryWrapper);
    }

    /**
     * 根据父级id获取祖辈的列表
     * @param pid 父级id
     * @return 祖辈类目
     */
    @Override
    public List<Long> getParentList(Long pid) {
        List<Long> parentIdList = new ArrayList<>();
        if (pid == 0) {
            parentIdList.add(0L);
            return parentIdList;
        }
        // 获取父级类目的所有父级
        LambdaQueryWrapper<CategoryRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CategoryRelation::getChildCategoryId, pid);
        List<CategoryRelation> list = list(lambdaQueryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            // 父级的父级关系
            throw new GlobalException("找不到pid=["+ pid +"]的相关信息");
        }
        for (CategoryRelation categoryCategory : list) {
            parentIdList.add(categoryCategory.getFatherCategoryId());
        }
        // 把父级加进来
        parentIdList.add(pid);
        // 商品类目层级不能超过4级
        int maxLevel = 4;
        if (parentIdList.size() > maxLevel) {
            throw new GlobalException("商品类目层级不能超过4级");
        }
        return parentIdList;
    }

    /**
     * 根据cid获取所有父类
     * @param categoryId cid
     * @return 父级类目
     */
    @Override
    public List<CategoryRelation> findParentByCid(Long categoryId) {
        // 获取所有父级
        LambdaQueryWrapper<CategoryRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CategoryRelation::getChildCategoryId, categoryId);
        return list(lambdaQueryWrapper);
    }

    /**
     * 根据cid获取直属父类id
     * @param categoryId cid
     * @return 父级类目id
     */
    @Override
    public CategoryRelation findParentIdByCid(Long categoryId) {
        LambdaQueryWrapper<CategoryRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CategoryRelation::getChildCategoryId, categoryId);
        lambdaQueryWrapper.eq(CategoryRelation::getIsSub, 1);
        return getOne(lambdaQueryWrapper);
    }

}
