package com.yjs.cloud.learning.member.biz.level.bean;

import com.yjs.cloud.learning.member.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 请求返回结果
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class MemberLevelGetPageResponse extends PageResponse {

    @ApiModelProperty(value = "列表")
    private List<MemberLevelResponse> list;

}
