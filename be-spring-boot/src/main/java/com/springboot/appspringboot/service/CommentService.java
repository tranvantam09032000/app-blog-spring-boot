package com.springboot.appspringboot.service;
import com.springboot.appspringboot.dto.request.CommentCreateRequest;
import com.springboot.appspringboot.dto.request.CommentUpdateRequest;
import com.springboot.appspringboot.dto.response.CommentResponse;
import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.mapper.CommentMapper;
import com.springboot.appspringboot.repository.CommentRepository;
import com.springboot.appspringboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public CommentResponse createComment(CommentCreateRequest request){
        Post post = postRepository.findById(request.getPostId().toString()).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = Comment.builder()
                .fullName(request.getFullName())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .post(post)
                .build();

        commentRepository.save(comment);
        CommentResponse response = commentMapper.toCommentResponse(request);
        response.setId(comment.getId());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUpdatedAt(comment.getUpdatedAt());
        return response;
    }

    public CommentResponse updateComment(Integer id, CommentUpdateRequest request) {
        Comment commentById = commentRepository.findById(id.toString()).orElseThrow(() -> new RuntimeException("Comment not found"));

        Post post = postRepository.getReferenceById(request.getPostId().toString());

        Comment comment = Comment.builder()
                .id(id)
                .fullName(request.getFullName())
                .content(request.getContent())
                .updatedAt(LocalDateTime.now())
                .createdAt(commentById.getCreatedAt())
                .post(post)
                .build();
        commentRepository.save(comment);
        CommentResponse response = commentMapper.toCommentResponse(request);
        response.setId(comment.getId());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUpdatedAt(comment.getUpdatedAt());
        return response;
    }

    public void deleteComment(Integer id){
        commentRepository.deleteById(id.toString());
    }
}
