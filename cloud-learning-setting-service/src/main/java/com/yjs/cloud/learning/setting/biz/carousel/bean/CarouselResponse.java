package com.yjs.cloud.learning.setting.biz.carousel.bean;

import com.yjs.cloud.learning.setting.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类目返回对象
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class CarouselResponse extends BaseResponse {

    @ApiModelProperty(value = "类目名称", example = "学习")
    private String carouselJson;

}
