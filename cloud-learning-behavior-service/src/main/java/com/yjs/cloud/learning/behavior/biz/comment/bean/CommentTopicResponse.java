package com.yjs.cloud.learning.behavior.biz.comment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课程
 *
 * @author Bill.lai
 * @since 2/1/21
 */
@ApiModel
@Data
public class CommentTopicResponse {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

}
