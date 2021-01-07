package com.yjs.cloud.learning.learn.biz.category.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品类目参数
 *
 * @author Bill.lai
 * @since 2020/7/7
 */
@ApiModel
@Data
public class CategoryUpdateRequest {

    /**
     * 类目id
     */
    @ApiModelProperty(value = "类目ID", example = "1", required = true)
    private Long id;

    /**
     * 父类目id
     */
    @ApiModelProperty(value = "父级类目ID，不填为0", example = "1", required = true)
    private Long pid;

    /**
     * 类目名称
     */
    @ApiModelProperty(value = "类目名称", example = "女装/女士精品", required = true)
    private String name;

    /**
     * 排序序号
     */
    @ApiModelProperty(value = "排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数", example = "1", required = true)
    private Integer sortOrder;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示", example = "true", required = true)
    private Boolean isShow;

    /**
     * 是否在首页显示
     */
    @ApiModelProperty(value = "是否在首页显示", example = "true", required = true)
    private Boolean isShowIndex;

    /**
     * 分类图片
     */
    @ApiModelProperty(value = "分类图片", example = "http://t.com/x.jpg", required = true)
    private String image;

}
