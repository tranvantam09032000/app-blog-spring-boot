package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.CommentRequestDTO;
import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.entity.Post;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentRequestToComment(CommentRequestDTO commentRequestDTO, Post post) {
        if ( commentRequestDTO == null && post == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( commentRequestDTO != null ) {
            comment.setCreatedAt( commentRequestDTO.getCreatedAt() );
            comment.setUpdatedAt( commentRequestDTO.getUpdatedAt() );
            comment.setId( commentRequestDTO.getId() );
            comment.setFullName( commentRequestDTO.getFullName() );
            comment.setContent( commentRequestDTO.getContent() );
        }
        comment.setPost( post );

        return comment;
    }
}
