package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.PostRequestDTO;
import com.springboot.appspringboot.dto.response.PostResponseDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.entity.PostContent;
import com.springboot.appspringboot.entity.Tag;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PostMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "id", source = "postRequestDTO.id")
    @Mapping(target = "title", source = "postRequestDTO.title")
    Post postRequestToPost(PostRequestDTO postRequestDTO, Author author, Category category, Set<Tag> tags);

    @AfterMapping
    default void setPost(PostRequestDTO postRequestDTO, @MappingTarget Post post) {
        Set<PostContent> postContents = Arrays.stream(postRequestDTO.getContents())
                .map(content -> {
                    content.setPost(post);
                    return content;
                }).collect(Collectors.toSet());

        post.setContents(postContents);
    }

    @Mapping(source = "contents", target = "contents")
    PostResponseDTO postToPostResponseDTO(Post post);
}
