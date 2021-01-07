package com.yjs.cloud.learning.auth.biz.authority.bean;

import com.yjs.cloud.learning.auth.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;


/**
 * 权限分页列表请求返回结果
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class AuthorityGetPageResponse extends PageResponse {

    @ApiModelProperty(value = "权限列表")
    private List<Map<String, Object>> list;

}
