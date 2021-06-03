package com.yjs.cloud.learning.learn.biz.lesson.web;

import com.yjs.cloud.learning.learn.biz.lesson.bean.*;
import com.yjs.cloud.learning.learn.biz.lesson.enums.LessonStatus;
import com.yjs.cloud.learning.learn.biz.lesson.service.LessonService;
import com.yjs.cloud.learning.learn.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 直播频道 前端控制器
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@Api
@AllArgsConstructor
@RestController
public class LessonController extends BaseController {

    private final LessonService lessonService;

    @ApiOperation(value = "创建课程", notes = "创建一个课程", httpMethod = "POST")
    @PostMapping("/lesson")
    public LessonResponse create(@RequestBody LessonCreateRequest lessonCreateRequest) {
        return lessonService.create(lessonCreateRequest);
    }

    @ApiOperation(value = "更新课程", notes = "更新课程信息", httpMethod = "PUT")
    @PutMapping("/lesson")
    public LessonResponse update(@RequestBody LessonUpdateRequest lessonUpdateRequest) {
        return lessonService.update(lessonUpdateRequest);
    }

    @ApiOperation(value = "获取课程列表", notes = "获取课程列表", httpMethod = "GET")
    @GetMapping("/lesson/list")
    public LessonListResponse list(LessonListRequest lessonListRequest) {
        return lessonService.list(lessonListRequest);
    }

    @ApiOperation(value = "获取课程信息", notes = "获取课程信息", httpMethod = "GET")
    @GetMapping("/lesson")
    public LessonResponse get(LessonGetRequest lessonGetRequest) {
        return lessonService.get(lessonGetRequest);
    }

    @ApiOperation(value = "删除课程", notes = "删除课程信息", httpMethod = "DELETE")
    @DeleteMapping("/lesson")
    public void delete(@RequestBody LessonDeleteRequest lessonDeleteRequest) {
        lessonService.delete(lessonDeleteRequest);
    }

    @ApiOperation(value = "发布课程", notes = "发布课程", httpMethod = "PUT")
    @PutMapping("/lesson/publish")
    public void publish(@RequestBody LessonPublishRequest lessonPublishRequest) {
        lessonService.publish(lessonPublishRequest);
    }

    @ApiOperation(value = "取消发布课程", notes = "取消发布课程", httpMethod = "PUT")
    @PutMapping("/lesson/un-publish")
    public void unPublish(@RequestBody LessonPublishRequest lessonPublishRequest) {
        lessonService.unPublish(lessonPublishRequest);
    }

    @ApiOperation(value = "获取热门推荐课程列表", notes = "获取热门推荐课程列表", httpMethod = "GET")
    @GetMapping("/public-api/lesson/recommend")
    public List<LessonResponse> recommendList() {
        LessonListRequest lessonListRequest = new LessonListRequest();
        try {
            lessonListRequest.setMemberId(getLoginUserId());
            lessonListRequest.setDepartmentId(getDepartmentId());
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
        return lessonService.list(lessonListRequest).getList();
    }

    @ApiOperation(value = "获取分类热门推荐课程列表", notes = "获取分类热门推荐课程列表", httpMethod = "GET")
    @GetMapping("/public-api/lesson/hot")
    public List<LessonResponse> recommendList(LessonListRequest lessonListRequest) {
        try {
            lessonListRequest.setMemberId(getLoginUserId());
            lessonListRequest.setDepartmentId(getDepartmentId());
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
        return lessonService.list(lessonListRequest).getList();
    }

    @ApiOperation(value = "获取课程信息(用户端)", notes = "获取课程信息", httpMethod = "GET")
    @GetMapping("/public-api/lesson")
    public LessonResponse getLesson(LessonGetRequest lessonGetRequest) {
        lessonGetRequest.setMemberId(getLoginUserId());
        lessonGetRequest.setDepartmentId(getDepartmentId());
        lessonGetRequest.setStatus(LessonStatus.published);
        return lessonService.get(lessonGetRequest);
    }

    @ApiOperation(value = "获取课程列表", notes = "获取课程列表", httpMethod = "GET")
    @GetMapping("/public-api/lesson/list")
    public LessonListResponse publicList(LessonListRequest lessonListRequest) {
        try {
            lessonListRequest.setMemberId(getLoginUserId());
            lessonListRequest.setDepartmentId(getDepartmentId());
        } catch (Exception ignored) {
            LessonListResponse response = new LessonListResponse();
            response.setCurrent(lessonListRequest.getCurrent());
            response.setSize(lessonListRequest.getSize());
            response.setTotal(0L);
            response.setPages(0L);
            response.setList(new ArrayList<>());
            return response;
        }
        return lessonService.list(lessonListRequest);
    }

    @ApiOperation(value = "获取收藏的课程列表", notes = "获取收藏的课程列表", httpMethod = "GET")
    @GetMapping("/auth-api/lesson/favorite/list")
    public LessonListResponse favoriteList(LessonFavoriteListRequest lessonFavoriteListRequest) {
        lessonFavoriteListRequest.setMemberId(getLoginUserId());
        return lessonService.favoriteList(lessonFavoriteListRequest);
    }

    @ApiOperation(value = "获取会员的学习课程列表", notes = "获取会员的学习课程列表", httpMethod = "GET")
    @GetMapping("/auth-api/lesson/member/learn/list")
    public LessonListResponse learnList(LessonLearnListRequest lessonLearnListRequest) {
        lessonLearnListRequest.setMemberId(getLoginUserId());
        return lessonService.learnList(lessonLearnListRequest);
    }
}
