package com.yjs.cloud.learning.learn.biz.record.bean;

import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpResponse;
import com.yjs.cloud.learning.learn.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 学习记录
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class SignUpGetPageListResponse extends PageResponse {

    @ApiModelProperty(value = "列表")
    private List<SignUpResponse> list;

}
