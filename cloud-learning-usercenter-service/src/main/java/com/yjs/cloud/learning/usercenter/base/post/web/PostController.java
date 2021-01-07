package com.yjs.cloud.learning.usercenter.base.post.web;

import com.yjs.cloud.learning.usercenter.base.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 岗位控制器
 *
 * @author Bill.lai
 * @since 2019-11-05
 */
@RestController
@AllArgsConstructor
public class PostController {

    private PostService postService;

//    /**
//     * 创建岗位
//     * @param post 岗位实体
//     * @return Post 创建的岗位
//     */
//    @PostMapping("/posts")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Post insert(@RequestBody Post post) {
//        postService.save(post);
//        return post;
//    }
//
//    /**
//     * 删除岗位
//     * @param id 岗位id
//     */
//    @DeleteMapping("/posts/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePost(@PathVariable Long id) {
//        postService.removeById(id);
//    }
//
//    /**
//     * 更新岗位
//     * @param post 岗位实体
//     * @return 更新后的岗位
//     */
//    @PutMapping("/posts")
//    public Post updateUser(@RequestBody Post post) {
//        postService.updateById(post);
//        return post;
//    }
//
//    /**
//     * 获取岗位列表
//     * @return 岗位列表
//     */
//    @GetMapping("/posts")
//    public List<Post> list() {
//        return postService.list();
//    }
//
//    /**
//     * 获取岗位实体
//     * @param id 岗位id
//     * @return 岗位实体
//     */
//    @GetMapping("/posts/{id}")
//    public Post getUserById(@PathVariable Long id) {
//        return postService.getById(id);
//    }

}
