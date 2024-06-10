package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.CommentCreateRequest;
import com.springboot.appspringboot.dto.request.CommentUpdateRequest;
import com.springboot.appspringboot.dto.response.CommentResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentResponse toComment(CommentCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        CommentResponse.CommentResponseBuilder commentResponse = CommentResponse.builder();

        commentResponse.fullName( request.getFullName() );
        commentResponse.content( request.getContent() );
        commentResponse.createdAt( request.getCreatedAt() );
        commentResponse.updatedAt( request.getUpdatedAt() );
        commentResponse.postId( request.getPostId() );

        return commentResponse.build();
    }

    @Override
    public CommentResponse toCommentResponse(CommentCreateRequest comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse.CommentResponseBuilder commentResponse = CommentResponse.builder();

        commentResponse.fullName( comment.getFullName() );
        commentResponse.content( comment.getContent() );
        commentResponse.createdAt( comment.getCreatedAt() );
        commentResponse.updatedAt( comment.getUpdatedAt() );
        commentResponse.postId( comment.getPostId() );

        return commentResponse.build();
    }

    @Override
    public CommentResponse toCommentResponse(CommentUpdateRequest comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse.CommentResponseBuilder commentResponse = CommentResponse.builder();

        commentResponse.id( comment.getId() );
        commentResponse.fullName( comment.getFullName() );
        commentResponse.content( comment.getContent() );
        commentResponse.createdAt( comment.getCreatedAt() );
        commentResponse.updatedAt( comment.getUpdatedAt() );
        commentResponse.postId( comment.getPostId() );

        return commentResponse.build();
    }
}
