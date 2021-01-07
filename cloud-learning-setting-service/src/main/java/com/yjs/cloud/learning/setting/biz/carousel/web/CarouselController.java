package com.yjs.cloud.learning.setting.biz.carousel.web;

import com.yjs.cloud.learning.setting.biz.carousel.bean.CarouselCreateRequest;
import com.yjs.cloud.learning.setting.biz.carousel.bean.CarouselResponse;
import com.yjs.cloud.learning.setting.biz.carousel.service.CarouselService;
import com.yjs.cloud.learning.setting.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 首页轮播图 前端控制器
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@AllArgsConstructor
@RestController
@Api(tags = "首页轮播图")
public class CarouselController extends BaseController {

    private final CarouselService categoryService;

    @ApiOperation(value = "获取轮播图", tags = "首页轮播图", notes = "获取轮播图", httpMethod = "GET")
    @GetMapping("/public-api/carousel")
    public CarouselResponse get() {
        return categoryService.get();
    }

    @ApiOperation(value = "新增修改轮播图", tags = "首页轮播图", notes = "新增修改轮播图", httpMethod = "GET")
    @PostMapping("/carousel")
    public CarouselResponse create(@RequestBody CarouselCreateRequest carouselCreateRequest) {
        return categoryService.create(carouselCreateRequest);
    }

}
