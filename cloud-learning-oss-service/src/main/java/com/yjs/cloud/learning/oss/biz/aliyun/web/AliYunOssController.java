package com.yjs.cloud.learning.oss.biz.aliyun.web;

import com.yjs.cloud.learning.oss.biz.aliyun.bean.DeleteFileRequest;
import com.yjs.cloud.learning.oss.biz.aliyun.service.AliYunOssService;
import com.yjs.cloud.learning.oss.common.util.StringUtils;
import com.yjs.cloud.learning.oss.common.web.GlobalException;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

/**
 * <p>
 * 图片上传控制器
 * </p>
 *
 * @author tao.bai
 * @since 2020-09-03
 */
@RestController
@AllArgsConstructor
@Api(tags = "文件上传管理")
public class AliYunOssController {

    private final AliYunOssService aliYunOssService;

    @ApiOperation(value = "文件上传", notes = "文件上传", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "service", paramType = "path", dataType = "String", value = "服务名", required = true),
            @ApiImplicitParam(name = "module", paramType = "path", dataType = "String", value = "模块名"),
            @ApiImplicitParam(name = "fileType", paramType = "path", dataType = "文件类型", value = "模块名")
    })
    @PostMapping(value = {"/{service}/{module}/{fileType}", "/{service}/{fileType}"})
    public String upload(@RequestParam("file") MultipartFile file,
                         @PathVariable("service") String service,
                         @PathVariable(value = "fileType", required = false) String fileType,
                         @PathVariable(value = "module", required = false) String module) {
        if (file == null) {
            throw new GlobalException("文件不能为空!");
        }
        if (StringUtils.isEmpty(service)) {
            throw new GlobalException("service不能为空!");
        }
        if (module != null) {
            module = module + "/";
        }
        if (fileType != null) {
            fileType = fileType + "/";
        } else {
            fileType = "image/";
        }
        String path = "test/" + service + "/" + module + "/" + fileType;
        return aliYunOssService.upload(file, path + System.currentTimeMillis() + new Random().nextInt());
    }

    @ApiOperation(value = "文件删除", notes = "文件删除", httpMethod = "DELETE")
    @DeleteMapping("/file")
    public void delete(@RequestBody DeleteFileRequest deleteFileRequest) {
        if (deleteFileRequest.getPath() == null) {
            throw new GlobalException("文件路劲不能为空!");
        }
        aliYunOssService.delete(deleteFileRequest.getPath());
    }
}
