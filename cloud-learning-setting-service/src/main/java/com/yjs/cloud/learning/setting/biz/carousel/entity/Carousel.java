package com.yjs.cloud.learning.setting.biz.carousel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.setting.biz.carousel.bean.CarouselResponse;
import com.yjs.cloud.learning.setting.common.entity.BaseEntity;
import com.yjs.cloud.learning.setting.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 首页导航栏
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_carousel")
public class Carousel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 首页导航栏Item json字符串
     */
    private String carouselJson;

    public CarouselResponse convert() {
        CarouselResponse bean = new CarouselResponse();
        BeanCopierUtils.copy(this, bean);
        return bean;
    }
}
