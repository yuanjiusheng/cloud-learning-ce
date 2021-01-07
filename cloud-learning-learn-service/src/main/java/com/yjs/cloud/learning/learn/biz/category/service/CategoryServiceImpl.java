package com.yjs.cloud.learning.learn.biz.category.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yjs.cloud.learning.learn.biz.category.bean.*;
import com.yjs.cloud.learning.learn.biz.category.entity.Category;
import com.yjs.cloud.learning.learn.biz.category.entity.CategoryRelation;
import com.yjs.cloud.learning.learn.biz.category.mapper.CategoryMapper;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@AllArgsConstructor
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRelationService categoryRelationService;

    /**
     * 获取类目列表
     * @param categoryAdminListRequest 请求参数
     * @return 类目列表
     */
    @Override
    public List<CategoryResponse> list(CategoryAdminListRequest categoryAdminListRequest) {
        return categoryMapper.selectAdminList(categoryAdminListRequest);
    }

    /**
     * 获取类目详情
     * @param id 类目id
     * @return 类目
     */
    @Override
    public CategoryResponse get(Long id) {
        Category category = getById(id);
        if (category != null) {
            CategoryResponse categoryResponse = category.convert();
            CategoryRelation parent = categoryRelationService.findParentIdByCid(id);
            if (parent != null) {
                categoryResponse.setPid(parent.getFatherCategoryId());
            }
            return categoryResponse;
        }
        return null;
    }

    /**
     * 创建类目
     * @param categoryCreateRequest 类目参数
     * @return 类目
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryResponse create(CategoryCreateRequest categoryCreateRequest) {
        Category category = new Category();
        category.setName(categoryCreateRequest.getName());
        category.setSortOrder(categoryCreateRequest.getSortOrder());
        category.setIsShow(categoryCreateRequest.getIsShow());
        category.setIsShowIndex(categoryCreateRequest.getIsShowIndex());
        category.setSortOrder(categoryCreateRequest.getSortOrder());
        category.setImage(categoryCreateRequest.getImage());
        // 获取类目级别
        List<Long> parentList = categoryRelationService.getParentList(categoryCreateRequest.getPid());
        category.setLevel(parentList.size() > 0 ? parentList.size() : 1);
        // 保存商品类目
        save(category);
        // 保存商品类目的关系
        categoryCreateRequest.setId(category.getId());
        categoryRelationService.create(categoryCreateRequest);
        return category.convert();
    }

    /**
     * 更新类目
     * @param categoryUpdateRequest 类目
     * @return 类目
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryResponse update(CategoryUpdateRequest categoryUpdateRequest) {
        Category category = new Category();
        category.setId(categoryUpdateRequest.getId());
        category.setName(categoryUpdateRequest.getName());
        category.setSortOrder(categoryUpdateRequest.getSortOrder());
        category.setIsShow(categoryUpdateRequest.getIsShow());
        category.setIsShowIndex(categoryUpdateRequest.getIsShowIndex());
        category.setImage(categoryUpdateRequest.getImage());
        List<Long> parentList = categoryRelationService.getParentList(categoryUpdateRequest.getPid());
        category.setLevel(parentList.size() > 0 ? parentList.size() : 1);
        updateById(category);
        // 更新商品类目的关系
        categoryRelationService.update(categoryUpdateRequest);
        return category.convert();
    }

    /**
     * 删除类目
     * @param id 类目id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        CategoryAdminListRequest categoryAdminListRequest = new CategoryAdminListRequest();
        categoryAdminListRequest.setId(id);
        categoryAdminListRequest.setFetchAll(true);
        List<CategoryResponse> categoryResponseList = list(categoryAdminListRequest);
        if (!CollectionUtils.isEmpty(categoryResponseList) && categoryResponseList.size() > 1) {
            throw new GlobalException("该类目下存在子类目，不允许删除！");
        }
        // 删除商品类目
        removeById(id);
        // 删除商品类目关系
        categoryRelationService.deleteByChildCategoryId(id);
    }

    /**
     * 上传类目图片
     * @param categoryImageUploadRequest 参数
     * @return 返回结果
     */
    @Override
    public CategoryImageUploadResponse imageUpload(CategoryImageUploadRequest categoryImageUploadRequest) {
        return null;
    }

    /**
     * 删除类目图片
     * @param categoryImageDeleteRequest 参数
     * @return 返回结果
     */
    @Override
    public CategoryImageDeleteResponse imageDelete(CategoryImageDeleteRequest categoryImageDeleteRequest) {
        return null;
    }

    /**
     * 修改类目显示状态
     * @param categoryUpdateIsShowRequest 参数
     */
    @Override
    public void updateIsShow(CategoryUpdateIsShowRequest categoryUpdateIsShowRequest) {
        Category category = getById(categoryUpdateIsShowRequest.getId());
        if (category == null) {
            throw new GlobalException("没有找到相关的类目");
        }
        category.setIsShow(categoryUpdateIsShowRequest.getIsShow());
        updateById(category);
    }

    /**
     * 修改类目首页显示状态
     * @param categoryUpdateIsShowIndexRequest 参数
     */
    @Override
    public void updateIsShowIndex(CategoryUpdateIsShowIndexRequest categoryUpdateIsShowIndexRequest) {
        Category category = getById(categoryUpdateIsShowIndexRequest.getId());
        if (category == null) {
            throw new GlobalException("没有找到相关的类目");
        }
        category.setIsShowIndex(categoryUpdateIsShowIndexRequest.getIsShowIndex());
        updateById(category);
    }

    /**
     * 获取类目列表
     * @param categoryListRequest 参数
     * @return 类目列表
     */
    @Override
    public List<CategoryResponse> list(CategoryListRequest categoryListRequest) {
        return categoryMapper.selectList(categoryListRequest);
    }

}
