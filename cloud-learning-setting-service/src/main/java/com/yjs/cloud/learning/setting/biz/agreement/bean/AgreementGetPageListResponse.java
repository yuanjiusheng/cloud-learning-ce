package com.yjs.cloud.learning.setting.biz.agreement.bean;

import com.yjs.cloud.learning.setting.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 协议文章
 *
 * @author Bill.lai
 * @since 12/29/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class AgreementGetPageListResponse extends PageResponse {

    @ApiModelProperty(value = "文章列表")
    private List<AgreementResponse> list;

}
