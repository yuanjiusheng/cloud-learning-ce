package com.yjs.cloud.learning.learn.biz.lesson.web;

import com.yjs.cloud.learning.learn.biz.lesson.bean.*;
import com.yjs.cloud.learning.learn.biz.lesson.service.LessonChapterService;
import com.yjs.cloud.learning.learn.common.controller.BaseController;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 课程章节控制器
 *
 * @author Bill.lai
 * @since 2020/10/23
 */
@Api(tags = "课程章节管理")
@AllArgsConstructor
@RestController
public class LessonChapterController extends BaseController {

    private final LessonChapterService lessonChapterService;

    @ApiOperation(value = "新增课程章节", notes = "新增课程章节", httpMethod = "POST")
    @PostMapping("/lesson/chapter")
    public LessonChapterResponse create(@RequestBody LessonChapterCreateRequest lessonChapterCreateRequest) {
        return lessonChapterService.create(lessonChapterCreateRequest);
    }

    @ApiOperation(value = "修改课程章节", notes = "修改课程章节", httpMethod = "PUT")
    @PutMapping("/lesson/chapter")
    public LessonChapterResponse update(@RequestBody LessonChapterUpdateRequest lessonChapterUpdateRequest) {
        return lessonChapterService.update(lessonChapterUpdateRequest);
    }

    @ApiOperation(value = "删除课程章节", notes = "删除课程章节", httpMethod = "DELETE")
    @DeleteMapping("/lesson/chapter")
    public void delete(@RequestBody LessonChapterDeleteRequest lessonChapterDeleteRequest) {
        lessonChapterService.delete(lessonChapterDeleteRequest);
    }

    @ApiOperation(value = "获取课程章节列表（管理员端）", notes = "获取课程章节列表（管理员端）", httpMethod = "GET")
    @GetMapping("/lesson/chapter/list")
    public LessonChapterGetListResponse getList(LessonChapterGetListRequest lessonChapterGetListRequest) {
        if (lessonChapterGetListRequest.getLessonId() == null) {
            throw new GlobalException("课程id为必填参数");
        }
        return lessonChapterService.getList(lessonChapterGetListRequest);
    }

    @ApiOperation(value = "获取课程章节列表", notes = "获取课程章节列表", httpMethod = "GET")
    @GetMapping("/public-api/lesson/chapter/list")
    public LessonChapterGetListResponse getLessonList(LessonChapterGetListRequest lessonChapterGetListRequest) {
        if (lessonChapterGetListRequest.getLessonId() == null) {
            throw new GlobalException("课程id为必填参数");
        }
        return lessonChapterService.getList(lessonChapterGetListRequest);
    }

}
