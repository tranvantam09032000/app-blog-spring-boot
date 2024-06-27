package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.CommentRequestDTO;
import com.springboot.appspringboot.dto.request.PostRequestDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.entity.Tag;
import com.springboot.appspringboot.repository.AuthorRepository;
import com.springboot.appspringboot.repository.CategoryRepository;
import com.springboot.appspringboot.repository.PostRepository;
import com.springboot.appspringboot.repository.TagRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CommentMapper {

    @Mapping(target = "post", source = "post")
    @Mapping(target = "createdAt", source = "commentRequestDTO.createdAt")
    @Mapping(target = "updatedAt", source = "commentRequestDTO.updatedAt")
    @Mapping(target = "id", source = "commentRequestDTO.id")
    Comment commentRequestToComment(CommentRequestDTO commentRequestDTO, Post post);
}
