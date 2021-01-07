package com.yjs.cloud.learning.learn.biz.lesson.web;

import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionCreateRequest;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionDeleteRequest;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionResponse;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionUpdateRequest;
import com.yjs.cloud.learning.learn.biz.lesson.service.LessonChapterSectionService;
import com.yjs.cloud.learning.learn.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 课程章节内容控制器
 *
 * @author Bill.lai
 * @since 2020/10/23
 */
@Api(tags = "课程章节管理")
@AllArgsConstructor
@RestController
public class LessonChapterSectionController extends BaseController {

    private final LessonChapterSectionService lessonChapterSectionService;

    @ApiOperation(value = "新增课程章节内容", notes = "新增课程章节内容", httpMethod = "POST")
    @PostMapping("/lesson/chapter-section")
    public LessonChapterSectionResponse create(@RequestBody LessonChapterSectionCreateRequest lessonChapterSectionCreateRequest) {
        return lessonChapterSectionService.create(lessonChapterSectionCreateRequest);
    }

    @ApiOperation(value = "修改课程章节内容", notes = "修改课程章节内容", httpMethod = "PUT")
    @PutMapping("/lesson/chapter-section")
    public LessonChapterSectionResponse update(@RequestBody LessonChapterSectionUpdateRequest lessonChapterSectionUpdateRequest) {
        return lessonChapterSectionService.update(lessonChapterSectionUpdateRequest);
    }

    @ApiOperation(value = "删除课程章节内容", notes = "删除课程章节内容", httpMethod = "DELETE")
    @DeleteMapping("/lesson/chapter-section")
    public void delete(@RequestBody LessonChapterSectionDeleteRequest lessonChapterSectionDeleteRequest) {
        lessonChapterSectionService.delete(lessonChapterSectionDeleteRequest);
    }

}
