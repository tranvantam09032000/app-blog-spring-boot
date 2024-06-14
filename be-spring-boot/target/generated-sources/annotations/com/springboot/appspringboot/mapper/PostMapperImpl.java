package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.PostRequestDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.entity.Tag;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post createCarPartDTO(PostRequestDTO postRequestDTO, Post post, Set<Tag> tags, Author author, Category category) {
        if ( postRequestDTO == null ) {
            return post;
        }

        post.setTitle( postRequestDTO.getTitle() );
        post.setSubTitle( postRequestDTO.getSubTitle() );
        post.setContent( postRequestDTO.getContent() );
        post.setThumbnail( postRequestDTO.getThumbnail() );
        post.setCreatedAt( postRequestDTO.getCreatedAt() );
        post.setUpdatedAt( postRequestDTO.getUpdatedAt() );
        post.setPublished( postRequestDTO.getPublished() );

        return post;
    }
}
