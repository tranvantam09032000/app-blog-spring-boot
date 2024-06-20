package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.PostRequestDTO;
import com.springboot.appspringboot.dto.response.PostResponseDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.entity.PostContent;
import com.springboot.appspringboot.entity.Tag;
import com.springboot.appspringboot.repository.AuthorRepository;
import com.springboot.appspringboot.repository.CategoryRepository;
import com.springboot.appspringboot.repository.PostContentRepository;
import com.springboot.appspringboot.repository.PostRepository;
import com.springboot.appspringboot.repository.TagRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "tags", ignore = true)
    Post postRequestToPost(PostRequestDTO postRequestDTO,
                          @Context AuthorRepository authorRepository,
                          @Context CategoryRepository categoryRepository,
                          @Context TagRepository tagRepository,
                           @Context PostContentRepository postContentRepository);

    @AfterMapping
    default void setPost(PostRequestDTO postRequestDTO, @MappingTarget Post post,
                           @Context AuthorRepository authorRepository,
                           @Context CategoryRepository categoryRepository,
                           @Context TagRepository tagRepository,
                            @Context PostContentRepository postContentRepository
                          ) {
        Author author = authorRepository.getReferenceById(postRequestDTO.getAuthorId());
        Category category = categoryRepository.getReferenceById(postRequestDTO.getCategoryId());
        List<Tag> listTag = tagRepository.findAllById(List.of(postRequestDTO.getTags()));
        Set<Tag> tags = new HashSet<>(listTag);
        Set<PostContent> postContents = Arrays.stream(postRequestDTO.getContents()).peek(content -> content.setPost(post)).collect(Collectors.toSet());
//        Integer[] postContentIds = Arrays.stream(postRequestDTO.getContents()).map(content -> content.getId()).toArray(Integer[]::new);
//        postContentRepository.deleteAllByPost_IdAndIdEqualsIgnoreCase(post.getId(), postContentIds);

        post.setContents(postContents);
        post.setAuthor(author);
        post.setCategory(category);
        post.setTags(tags);
    }
    @Mapping(source = "contents", target = "contents")
    PostResponseDTO postToPostResponseDTO(Post post);
}
