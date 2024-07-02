package com.springboot.appspringboot.controller;

import com.springboot.appspringboot.dto.request.*;
import com.springboot.appspringboot.dto.response.PostResponseDTO;
import com.springboot.appspringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @CrossOrigin
    @PostMapping
    Integer createPost(@RequestBody PostRequestDTO request) {
        return postService.createPost(request);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    Integer updatePost(@PathVariable Integer id, @RequestBody PostRequestDTO request) {
        return postService.updatePost(id, request);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    PostResponseDTO getPostByid(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @CrossOrigin
    @GetMapping()
    PostResponseDTO[] getPosts(@RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "10") Integer size,
                               @RequestParam(required = false) String categoryId) {
        return postService.getPosts(page, size, categoryId);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }

    @CrossOrigin
    @PostMapping("/like")
    boolean likePost(@RequestBody LikePostRequestDTO request) {
        return postService.likePost(request);
    }
}