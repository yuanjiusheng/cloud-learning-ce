package com.yjs.cloud.learning.learn.biz.category.web;

import com.yjs.cloud.learning.learn.biz.category.bean.*;
import com.yjs.cloud.learning.learn.biz.category.service.CategoryService;
import com.yjs.cloud.learning.learn.common.controller.BaseController;
import com.yjs.cloud.learning.learn.common.util.StringUtils;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@AllArgsConstructor
@RestController
@Api
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @ApiOperation(value = "获取类目列表", tags = "类目管理", notes = "获取类目列表", httpMethod = "GET")
    @GetMapping("/category/admin/list")
    public List<CategoryResponse> listOfAdmin(CategoryAdminListRequest categoryAdminListRequest) {
        return categoryService.list(categoryAdminListRequest);
    }

    @ApiOperation(value = "获取类目详情", tags = "类目", notes = "获取类目详情", httpMethod = "GET")
    @ApiParam(name = "id", value = "类目id", required = true)
    @GetMapping("/category/{id}")
    public CategoryResponse get(@PathVariable(value = "id") Long id) {
        return categoryService.get(id);
    }

    @ApiOperation(value = "添加类目", tags = "类目管理", notes = "添加类目", httpMethod = "POST")
    @PostMapping("/category")
    public CategoryResponse create(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        return categoryService.create(categoryCreateRequest);
    }

    @ApiOperation(value = "修改类目", tags = "类目管理", notes = "修改类目", httpMethod = "PUT")
    @PutMapping("/category")
    public CategoryResponse update(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        if (categoryUpdateRequest == null || categoryUpdateRequest.getId() == null || categoryUpdateRequest.getId() <= 0) {
            throw new GlobalException("商品类目id需大于0");
        }
        return categoryService.update(categoryUpdateRequest);
    }

    @ApiOperation(value = "删除类目", tags = "类目管理", notes = "删除类目", httpMethod = "DELETE")
    @DeleteMapping("/category/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

    @ApiOperation(value = "上传类目图片", tags = "类目管理", notes = "上传类目图片", httpMethod = "POST")
    @PostMapping("/category/image")
    public CategoryImageUploadResponse imageUpload(CategoryImageUploadRequest categoryImageUploadRequest) {
        if (categoryImageUploadRequest.getFile().isEmpty()) {
            throw new GlobalException("文件不能为空");
        }
        return categoryService.imageUpload(categoryImageUploadRequest);
    }

    @ApiOperation(value = "删除类目图片", tags = "类目管理", notes = "删除类目图片", httpMethod = "DELETE")
    @DeleteMapping("/category/image")
    public CategoryImageDeleteResponse imageDelete(CategoryImageDeleteRequest categoryImageDeleteRequest) {
        if (StringUtils.isEmpty(categoryImageDeleteRequest.getUrl())) {
            throw new GlobalException("文件路径不能为空");
        }
        return categoryService.imageDelete(categoryImageDeleteRequest);
    }

    @ApiOperation(value = "修改类目显示状态", tags = "类目管理", notes = "修改类目显示状态", httpMethod = "PUT")
    @PutMapping("/category/is-show")
    public void updateIsShow(@RequestBody CategoryUpdateIsShowRequest categoryUpdateIsShowRequest) {
        if (categoryUpdateIsShowRequest.getId() == null) {
            throw new GlobalException("id不能为空");
        }
        if (categoryUpdateIsShowRequest.getIsShow() == null) {
            throw new GlobalException("显示状态不能为空");
        }
        categoryService.updateIsShow(categoryUpdateIsShowRequest);
    }

    @ApiOperation(value = "修改类目首页显示状态", tags = "类目管理", notes = "修改类目首页显示状态", httpMethod = "PUT")
    @PutMapping("/category/is-show-index")
    public void updateIsShow(@RequestBody CategoryUpdateIsShowIndexRequest categoryUpdateIsShowIndexRequest) {
        if (categoryUpdateIsShowIndexRequest.getId() == null) {
            throw new GlobalException("id不能为空");
        }
        if (categoryUpdateIsShowIndexRequest.getIsShowIndex() == null) {
            throw new GlobalException("显示状态不能为空");
        }
        categoryService.updateIsShowIndex(categoryUpdateIsShowIndexRequest);
    }

    @ApiOperation(value = "获取类目列表", tags = "类目", notes = "获取类目列表", httpMethod = "GET")
    @GetMapping("/public-api/category/list")
    public List<CategoryResponse> list(CategoryListRequest categoryListRequest) {
        return categoryService.list(categoryListRequest);
    }

}
