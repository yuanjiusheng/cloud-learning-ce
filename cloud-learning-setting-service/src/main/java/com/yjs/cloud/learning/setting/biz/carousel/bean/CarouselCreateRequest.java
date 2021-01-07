package com.yjs.cloud.learning.setting.biz.carousel.bean;

import com.yjs.cloud.learning.setting.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 首页轮播图返回对象
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class CarouselCreateRequest extends BaseResponse {

    @ApiModelProperty(value = "轮播图JSON")
    private String carouselJson;

}
