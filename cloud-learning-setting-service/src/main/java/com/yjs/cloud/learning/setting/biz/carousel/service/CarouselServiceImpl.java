package com.yjs.cloud.learning.setting.biz.carousel.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.setting.biz.carousel.bean.CarouselCreateRequest;
import com.yjs.cloud.learning.setting.biz.carousel.bean.CarouselResponse;
import com.yjs.cloud.learning.setting.biz.carousel.entity.Carousel;
import com.yjs.cloud.learning.setting.biz.carousel.mapper.CarouselMapper;
import com.yjs.cloud.learning.setting.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 轮播图服务实现类
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@AllArgsConstructor
@Service
public class CarouselServiceImpl extends BaseServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    /**
     * 获取首页轮播图设置
     * @return 首页轮播图设置
     */
    @Override
    public CarouselResponse get() {
        List<Carousel> list = list();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).convert();
    }

    /**
     * 创建首页轮播图
     * @param carouselCreateRequest 参数
     * @return 轮播图
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CarouselResponse create(CarouselCreateRequest carouselCreateRequest) {
        // 先删除
        LambdaQueryWrapper<Carousel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        remove(lambdaQueryWrapper);
        // 新增
        Carousel carousel = new Carousel();
        carousel.setCarouselJson(carouselCreateRequest.getCarouselJson());
        save(carousel);
        return carousel.convert();
    }

}
