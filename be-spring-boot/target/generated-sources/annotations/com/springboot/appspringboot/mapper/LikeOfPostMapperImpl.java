package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.LikePostRequestDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.LikeOfPost;
import com.springboot.appspringboot.entity.Post;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class LikeOfPostMapperImpl implements LikeOfPostMapper {

    @Override
    public LikeOfPost likeOfPostRequestToLikeOfPost(LikePostRequestDTO likePostRequestDTO, Post post, Author author) {
        if ( likePostRequestDTO == null && post == null && author == null ) {
            return null;
        }

        LikeOfPost likeOfPost = new LikeOfPost();

        if ( likePostRequestDTO != null ) {
            likeOfPost.setId( likePostRequestDTO.getId() );
            likeOfPost.setCreatedAt( likePostRequestDTO.getCreatedAt() );
            likeOfPost.setUpdatedAt( likePostRequestDTO.getUpdatedAt() );
        }
        likeOfPost.setPost( post );
        likeOfPost.setAuthor( author );

        return likeOfPost;
    }
}
