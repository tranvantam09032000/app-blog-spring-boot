package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.CommentCreateRequest;
import com.springboot.appspringboot.dto.request.CommentUpdateRequest;
import com.springboot.appspringboot.dto.request.PostCreateRequest;
import com.springboot.appspringboot.dto.request.PostUpdateRequest;
import com.springboot.appspringboot.dto.response.CommentResponse;
import com.springboot.appspringboot.dto.response.PostResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResponse toComment(CommentCreateRequest request);

    CommentResponse toCommentResponse(CommentCreateRequest comment);
    CommentResponse toCommentResponse(CommentUpdateRequest comment);
}
