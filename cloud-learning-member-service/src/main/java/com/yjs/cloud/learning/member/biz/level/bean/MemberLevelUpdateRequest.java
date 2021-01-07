package com.yjs.cloud.learning.member.biz.level.bean;

import com.yjs.cloud.learning.member.biz.level.entity.MemberLevel;
import com.yjs.cloud.learning.member.common.util.BeanCopierUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@ApiModel
@Data
public class MemberLevelUpdateRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "等级名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "条件")
    private Long conditions;

    public MemberLevel convert() {
        MemberLevel entity = new MemberLevel();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }
}
