package com.yjs.cloud.learning.learn.biz.record.bean;

import com.yjs.cloud.learning.learn.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学习记录
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpGetPageListRequest extends PageRequest {

    @ApiModelProperty(value = "会员ID", hidden = true)
    private Long memberId;

}
