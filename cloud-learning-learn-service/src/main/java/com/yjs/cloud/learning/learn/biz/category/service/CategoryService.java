package com.yjs.cloud.learning.learn.biz.category.service;

import com.yjs.cloud.learning.learn.biz.category.bean.*;
import com.yjs.cloud.learning.learn.biz.category.entity.Category;
import com.yjs.cloud.learning.learn.common.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
public interface CategoryService extends IBaseService<Category> {

    /**
     * 获取类目列表
     * @param categoryAdminListRequest 请求参数
     * @return 类目列表
     */
    List<CategoryResponse> list(CategoryAdminListRequest categoryAdminListRequest);

    /**
     * 获取类目详情
     * @param id 类目id
     * @return 类目
     */
    CategoryResponse get(Long id);

    /**
     * 创建类目
     * @param categoryCreateRequest 商品参数
     * @return 类目
     */
    @Transactional(rollbackFor = Exception.class)
    CategoryResponse create(CategoryCreateRequest categoryCreateRequest);

    /**
     * 更新类目
     * @param categoryUpdateRequest 类目
     * @return 类目
     */
    @Transactional(rollbackFor = Exception.class)
    CategoryResponse update(CategoryUpdateRequest categoryUpdateRequest);

    /**
     * 删除类目
     * @param id 类目id
     */
    @Transactional(rollbackFor = Exception.class)
    void delete(Long id);

    /**
     * 上传类目图片
     * @param categoryImageUploadRequest 参数
     * @return 返回结果
     */
    CategoryImageUploadResponse imageUpload(CategoryImageUploadRequest categoryImageUploadRequest);

    /**
     * 删除类目图片
     * @param categoryImageDeleteRequest 参数
     * @return 返回结果
     */
    CategoryImageDeleteResponse imageDelete(CategoryImageDeleteRequest categoryImageDeleteRequest);

    /**
     * 修改类目显示状态
     * @param categoryUpdateIsShowRequest 参数
     */
    void updateIsShow(CategoryUpdateIsShowRequest categoryUpdateIsShowRequest);

    /**
     * 修改类目首页显示状态
     * @param categoryUpdateIsShowIndexRequest 参数
     */
    void updateIsShowIndex(CategoryUpdateIsShowIndexRequest categoryUpdateIsShowIndexRequest);

    /**
     * 获取类目列表
     * @param categoryListRequest 参数
     * @return 类目列表
     */
    List<CategoryResponse> list(CategoryListRequest categoryListRequest);
}
