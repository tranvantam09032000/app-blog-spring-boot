package com.springboot.appspringboot.controller;
import com.springboot.appspringboot.dto.request.CommentRequestDTO;
import com.springboot.appspringboot.entity.Comment;
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
    @GetMapping()
    List<Comment> getCommentByPost(@RequestParam(required = false) Integer postId) {
        return commentService.getCommentByPost(postId);
    }

    @CrossOrigin
    @PostMapping()
    Integer createComment(@RequestBody CommentRequestDTO request) {
        return commentService.createComment(request);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    Integer updateComment(@PathVariable Integer id, @RequestBody CommentRequestDTO request) {
        return commentService.updateComment(id, request);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }

}