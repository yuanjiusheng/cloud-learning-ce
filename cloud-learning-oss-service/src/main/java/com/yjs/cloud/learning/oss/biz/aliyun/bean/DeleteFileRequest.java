package com.yjs.cloud.learning.oss.biz.aliyun.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 删除文件请求
 *
 * @author Bill.lai
 * @since 11/30/20
 */
@ApiModel
@Data
public class DeleteFileRequest {

    @ApiModelProperty(value = "文件OSS路径")
    private String path;

}
