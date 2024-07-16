package com.springboot.appspringboot.mapper;
import com.springboot.appspringboot.dto.request.LikePostRequestDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.LikeOfPost;
import com.springboot.appspringboot.entity.Post;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface LikeOfPostMapper {
    @Mapping(target = "post", source = "post")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "id", source = "likePostRequestDTO.id")
    @Mapping(target = "createdAt", source = "likePostRequestDTO.createdAt")
    @Mapping(target = "updatedAt", source = "likePostRequestDTO.updatedAt")
    LikeOfPost likeOfPostRequestToLikeOfPost(LikePostRequestDTO likePostRequestDTO, Post post, Author author);
}
