package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.yjs.cloud.learning.learn.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程获取列表请求参数
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class LessonFavoriteListRequest extends PageRequest {

    @ApiModelProperty(value = "分类id", example = "1", hidden = true)
    private Long memberId;

}
