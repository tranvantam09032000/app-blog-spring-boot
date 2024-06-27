package com.springboot.appspringboot.service;

import com.springboot.appspringboot.dto.request.CommentRequestDTO;
import com.springboot.appspringboot.dto.request.CommentUpdateRequest;
import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.mapper.CommentMapper;
import com.springboot.appspringboot.repository.CommentRepository;
import com.springboot.appspringboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getCommentByPost(Integer postId) {
        return postId == null ?
                commentRepository.findAll(Sort.by("id").descending()) :
                commentRepository.findCommentsByPost_Id(postId, Sort.by("id"));
    }

    public Integer createComment(CommentRequestDTO request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = commentMapper.commentRequestToComment(request, post);
        commentRepository.save(comment);
        return comment.getId();
    }

    public Integer updateComment(Integer id, CommentRequestDTO request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment commentById = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        Comment comment = commentMapper.commentRequestToComment(request, post);
        commentRepository.save(comment);
        return comment.getId();
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
