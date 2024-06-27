package com.springboot.appspringboot.mapper;
import com.springboot.appspringboot.dto.request.CommentRequestDTO;
import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.entity.Post;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CommentMapper {

    @Mapping(target = "post", source = "post")
    @Mapping(target = "createdAt", source = "commentRequestDTO.createdAt")
    @Mapping(target = "updatedAt", source = "commentRequestDTO.updatedAt")
    @Mapping(target = "id", source = "commentRequestDTO.id")
    Comment commentRequestToComment(CommentRequestDTO commentRequestDTO, Post post);
}
