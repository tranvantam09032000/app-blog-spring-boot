package com.springboot.appspringboot.controller;

import com.springboot.appspringboot.dto.request.ApiResponse;
import com.springboot.appspringboot.dto.request.CommentCreateRequest;
import com.springboot.appspringboot.dto.request.CommentUpdateRequest;
import com.springboot.appspringboot.dto.response.CommentResponse;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.service.CategoryService;
import com.springboot.appspringboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @CrossOrigin
    @GetMapping("/{postId}")
    List<Comment> getCommentByPost(@RequestParam(required = false) Integer postId){
        return commentService.getCommentByPost(postId);
    }

    @CrossOrigin
    @PostMapping()
    CommentResponse createComment(@RequestBody CommentCreateRequest request){
        return commentService.createComment(request);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    CommentResponse updateComment(@PathVariable Integer id , @RequestBody CommentUpdateRequest request){
        return commentService.updateComment(id,request);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }

}