package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.biz.lesson.enums.LessonStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 课程获取列表请求参数
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@ApiModel
@Data
public class LessonListRequest {

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

    @ApiModelProperty(value = "分类id", example = "1")
    private Long cid;

    @ApiModelProperty(value = "状态")
    private LessonStatus status;

    @ApiModelProperty(value = "是否显示")
    private Boolean isShow;

    @ApiModelProperty(value = "分页页码")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private Integer pageSize = 20;

}
