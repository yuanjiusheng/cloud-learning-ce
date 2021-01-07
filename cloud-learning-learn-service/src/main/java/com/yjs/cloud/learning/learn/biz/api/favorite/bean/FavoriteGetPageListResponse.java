package com.yjs.cloud.learning.learn.biz.api.favorite.bean;

import com.yjs.cloud.learning.learn.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class FavoriteGetPageListResponse extends PageResponse {

    @ApiModelProperty(value = "列表")
    private List<FavoriteResponse> list;

}
