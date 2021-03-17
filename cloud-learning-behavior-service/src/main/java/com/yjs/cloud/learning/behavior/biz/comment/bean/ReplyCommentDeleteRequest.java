package com.yjs.cloud.learning.behavior.biz.comment.bean;

import com.yjs.cloud.learning.behavior.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class ReplyCommentDeleteRequest extends PageRequest {

    @ApiModelProperty(value = "id")
    private Long id;

}
