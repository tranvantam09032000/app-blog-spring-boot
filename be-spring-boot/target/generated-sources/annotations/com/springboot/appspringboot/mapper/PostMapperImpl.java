package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.PostRequestDTO;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.repository.AuthorRepository;
import com.springboot.appspringboot.repository.CategoryRepository;
import com.springboot.appspringboot.repository.TagRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post createCarPartDTO(PostRequestDTO postRequestDTO, AuthorRepository authorRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
        if ( postRequestDTO == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( postRequestDTO.getId() );
        post.setTitle( postRequestDTO.getTitle() );
        post.setSubTitle( postRequestDTO.getSubTitle() );
        post.setContent( postRequestDTO.getContent() );
        post.setThumbnail( postRequestDTO.getThumbnail() );
        post.setCreatedAt( postRequestDTO.getCreatedAt() );
        post.setUpdatedAt( postRequestDTO.getUpdatedAt() );
        post.setPublished( postRequestDTO.getPublished() );

        setAuthor( postRequestDTO, post, authorRepository, categoryRepository, tagRepository );

        return post;
    }
}
