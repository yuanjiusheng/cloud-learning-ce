package com.yjs.cloud.learning.behavior.biz.sensitiveword.web;

import com.yjs.cloud.learning.behavior.biz.sensitiveword.bean.*;
import com.yjs.cloud.learning.behavior.biz.sensitiveword.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 敏感词控制器
 *
 * @author Bill.lai
 * @since 2/3/21
 */
@Api(tags = "敏感词")
@AllArgsConstructor
@RestController
public class WordController {

    private final WordService wordService;

    @ApiOperation(value = "获取敏感词列表", notes = "获取敏感词列表", httpMethod = "GET")
    @GetMapping("/sensitive-word/list")
    public WordGetListResponse getList(WordGetListRequest wordGetListRequest) {
        return wordService.list(wordGetListRequest);
    }

    @ApiOperation(value = "新增敏感词", notes = "新增敏感词", httpMethod = "POST")
    @PostMapping("/sensitive-word")
    public void create(@RequestBody WordCreateRequest wordCreateRequest) {
        wordService.create(wordCreateRequest);
    }

    @ApiOperation(value = "修改敏感词", notes = "修改敏感词", httpMethod = "PUT")
    @PutMapping("/sensitive-word")
    public void update(@RequestBody WordUpdateRequest wordUpdateRequest) {
        wordService.update(wordUpdateRequest);
    }

    @ApiOperation(value = "删除敏感词", notes = "删除敏感词", httpMethod = "DELETE")
    @DeleteMapping("/sensitive-word")
    public void delete(@RequestBody WordDeleteRequest wordDeleteRequest) {
        wordService.delete(wordDeleteRequest);
    }

}
