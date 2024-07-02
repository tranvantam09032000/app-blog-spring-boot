package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.PostRequestDTO;
import com.springboot.appspringboot.dto.response.PostResponseDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.LikeOfPost;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.entity.PostContent;
import com.springboot.appspringboot.entity.Tag;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
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
    public Post postRequestToPost(PostRequestDTO postRequestDTO, Author author, Category category, Set<Tag> tags) {
        if ( postRequestDTO == null && author == null && category == null && tags == null ) {
            return null;
        }

        Post post = new Post();

        if ( postRequestDTO != null ) {
            post.setId( postRequestDTO.getId() );
            post.setTitle( postRequestDTO.getTitle() );
            post.setCreatedAt( postRequestDTO.getCreatedAt() );
            post.setUpdatedAt( postRequestDTO.getUpdatedAt() );
            post.setSubTitle( postRequestDTO.getSubTitle() );
            post.setThumbnail( postRequestDTO.getThumbnail() );
            post.setPublished( postRequestDTO.getPublished() );
            post.setContents( postContentArrayToPostContentSet( postRequestDTO.getContents() ) );
        }
        if ( author != null ) {
            post.setAuthor( author );
            Set<LikeOfPost> set1 = author.getLikeOfPosts();
            if ( set1 != null ) {
                post.setLikeOfPosts( new LinkedHashSet<LikeOfPost>( set1 ) );
            }
        }
        post.setCategory( category );
        Set<Tag> set = tags;
        if ( set != null ) {
            post.setTags( new LinkedHashSet<Tag>( set ) );
        }

        setPost( postRequestDTO, post );

        return post;
    }

    @Override
    public PostResponseDTO postToPostResponseDTO(Post post, Integer[] authorsOfLike) {
        if ( post == null && authorsOfLike == null ) {
            return null;
        }

        PostResponseDTO postResponseDTO = new PostResponseDTO();

        if ( post != null ) {
            postResponseDTO.setId( post.getId() );
            postResponseDTO.setTitle( post.getTitle() );
            postResponseDTO.setSubTitle( post.getSubTitle() );
            postResponseDTO.setThumbnail( post.getThumbnail() );
            postResponseDTO.setCreatedAt( post.getCreatedAt() );
            postResponseDTO.setUpdatedAt( post.getUpdatedAt() );
            postResponseDTO.setPublished( post.getPublished() );
            Set<Tag> set = post.getTags();
            if ( set != null ) {
                postResponseDTO.setTags( new ArrayList<Tag>( set ) );
            }
            Set<PostContent> set1 = post.getContents();
            if ( set1 != null ) {
                postResponseDTO.setContents( new ArrayList<PostContent>( set1 ) );
            }
            postResponseDTO.setAuthor( post.getAuthor() );
            postResponseDTO.setCategory( post.getCategory() );
        }
        Integer[] authorsOfLike1 = authorsOfLike;
        if ( authorsOfLike1 != null ) {
            postResponseDTO.setAuthorsOfLike( Arrays.copyOf( authorsOfLike1, authorsOfLike1.length ) );
        }

        return postResponseDTO;
    }

    protected Set<PostContent> postContentArrayToPostContentSet(PostContent[] postContentArray) {
        if ( postContentArray == null ) {
            return null;
        }

        Set<PostContent> set = new LinkedHashSet<PostContent>( Math.max( (int) ( postContentArray.length / .75f ) + 1, 16 ) );
        for ( PostContent postContent : postContentArray ) {
            set.add( postContent );
        }

        return set;
    }
}
