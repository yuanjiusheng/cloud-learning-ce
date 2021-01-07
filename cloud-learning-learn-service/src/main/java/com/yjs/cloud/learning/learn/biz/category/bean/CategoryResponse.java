package com.yjs.cloud.learning.learn.biz.category.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 类目返回对象
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@ApiModel
@Data
public class CategoryResponse {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "类目名称", example = "学习")
    private String name;

    @ApiModelProperty(value = "排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列", example = "1")
    private Integer sortOrder;

    @ApiModelProperty(value = "是否显示", example = "true")
    private Boolean isShow;

    @ApiModelProperty(value = "是否在首页显示", example = "true")
    private Boolean isShowIndex;

    @ApiModelProperty(value = "分类图片", example = "true")
    private String image;

    @ApiModelProperty(value = "类目级别", example = "true")
    private Integer level;

    @ApiModelProperty(value = "父级ID", example = "0")
    private Long pid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;
}
