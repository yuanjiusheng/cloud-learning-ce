package com.yjs.cloud.learning.learn.biz.lesson.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yjs.cloud.learning.learn.biz.lesson.enums.LessonStatus;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordResponse;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpResponse;
import com.yjs.cloud.learning.learn.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class LessonResponse extends BaseResponse {

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String code;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "封面图片")
    private String image;

    @ApiModelProperty(value = "状态")
    private LessonStatus status;

    @ApiModelProperty(value = "短语介绍")
    private String phrase;

    @ApiModelProperty(value = "活动描述")
    private String introduction;

    @ApiModelProperty(value = "分类id")
    private List<Long> cidList;

    @ApiModelProperty(value = "收藏数量")
    private Long favoriteNum;

    @ApiModelProperty(value = "点赞数量")
    private Long likeNum;

    @ApiModelProperty(value = "评论数量")
    private Long commentNum;

    @ApiModelProperty(value = "在学人数")
    private Long learnNum;

    @ApiModelProperty(value = "报名记录")
    private SignUpResponse signUp;

    @ApiModelProperty(value = "学习进度")
    private List<RecordResponse> recordList;

}
