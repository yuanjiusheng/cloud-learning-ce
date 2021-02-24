package com.yjs.cloud.learning.member.biz.level.bean;

import com.yjs.cloud.learning.member.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class MemberLevelGetPageRequest extends PageRequest {

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

}
