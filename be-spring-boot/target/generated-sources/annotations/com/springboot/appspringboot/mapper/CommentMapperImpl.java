package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.CommentRequestDTO;
import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.repository.PostRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentRequestToComment(CommentRequestDTO commentRequestDTO, PostRepository postRepository) {
        if ( commentRequestDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCreatedAt( commentRequestDTO.getCreatedAt() );
        comment.setUpdatedAt( commentRequestDTO.getUpdatedAt() );
        comment.setId( commentRequestDTO.getId() );
        comment.setFullName( commentRequestDTO.getFullName() );
        comment.setContent( commentRequestDTO.getContent() );

        setPost( commentRequestDTO, comment, postRepository );

        return comment;
    }
}
