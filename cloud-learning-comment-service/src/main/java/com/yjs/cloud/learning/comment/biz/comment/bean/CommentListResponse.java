package com.yjs.cloud.learning.comment.biz.comment.bean;

import com.yjs.cloud.learning.comment.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 返回结果
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class CommentListResponse extends PageResponse {

    @ApiModelProperty(value = "列表")
    private List<CommentResponse> list;

}
