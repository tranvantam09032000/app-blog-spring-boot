package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.CommentCreateRequest;
import com.springboot.appspringboot.dto.request.CommentUpdateRequest;
import com.springboot.appspringboot.dto.response.CommentResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentResponse toComment(CommentCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        CommentResponse.CommentResponseBuilder commentResponse = CommentResponse.builder();

        commentResponse.content( request.getContent() );
        commentResponse.createdAt( request.getCreatedAt() );
        commentResponse.fullName( request.getFullName() );
        commentResponse.postId( request.getPostId() );
        commentResponse.updatedAt( request.getUpdatedAt() );

        return commentResponse.build();
    }

    @Override
    public CommentResponse toCommentResponse(CommentCreateRequest comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse.CommentResponseBuilder commentResponse = CommentResponse.builder();

        commentResponse.content( comment.getContent() );
        commentResponse.createdAt( comment.getCreatedAt() );
        commentResponse.fullName( comment.getFullName() );
        commentResponse.postId( comment.getPostId() );
        commentResponse.updatedAt( comment.getUpdatedAt() );

        return commentResponse.build();
    }

    @Override
    public CommentResponse toCommentResponse(CommentUpdateRequest comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse.CommentResponseBuilder commentResponse = CommentResponse.builder();

        commentResponse.content( comment.getContent() );
        commentResponse.createdAt( comment.getCreatedAt() );
        commentResponse.fullName( comment.getFullName() );
        commentResponse.id( comment.getId() );
        commentResponse.postId( comment.getPostId() );
        commentResponse.updatedAt( comment.getUpdatedAt() );

        return commentResponse.build();
    }
}
