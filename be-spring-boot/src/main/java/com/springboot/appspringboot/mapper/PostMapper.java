package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.PostCreateRequest;
import com.springboot.appspringboot.dto.request.PostMapUpdateRequest;
import com.springboot.appspringboot.dto.request.PostUpdateRequest;
import com.springboot.appspringboot.dto.response.PostResponse;
import com.springboot.appspringboot.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
//    PostResponse toPost(PostCreateRequest request);
//
//    PostResponse toPostResponse(PostCreateRequest post);
//    PostResponse toPostResponse(PostUpdateRequest post);
}
