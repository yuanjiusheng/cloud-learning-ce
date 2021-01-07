package com.yjs.cloud.learning.setting.biz.carousel.service;

import com.yjs.cloud.learning.setting.biz.carousel.bean.CarouselCreateRequest;
import com.yjs.cloud.learning.setting.biz.carousel.bean.CarouselResponse;
import com.yjs.cloud.learning.setting.biz.carousel.entity.Carousel;
import com.yjs.cloud.learning.setting.common.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 首页轮播图 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
public interface CarouselService extends IBaseService<Carousel> {

    /**
     * 获取首页轮播图设置
     * @return 首页轮播图设置
     */
    CarouselResponse get();

    /**
     * 创建首页轮播图
     * @param carouselCreateRequest 参数
     * @return 首页轮播图
     */
    @Transactional(rollbackFor = Exception.class)
    CarouselResponse create(CarouselCreateRequest carouselCreateRequest);

}
