package com.springboot.appspringboot.service;
import com.springboot.appspringboot.dto.request.*;
import com.springboot.appspringboot.dto.response.PostResponse;
import com.springboot.appspringboot.entity.*;
import com.springboot.appspringboot.repository.AuthorRepository;
import com.springboot.appspringboot.repository.CategoryRepository;
import com.springboot.appspringboot.repository.PostRepository;
import com.springboot.appspringboot.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TagRepository tagRepository;
    public Integer createPost(PostCreateRequest request){

        Category category = categoryRepository.getReferenceById(request.getCategoryId().toString());
        Author author = authorRepository.getReferenceById(request.getAuthorId().toString());

        Post post = Post.builder()
                .title(request.getTitle())
                .subTitle(request.getSubTitle())
                .category(category)
                .author(author)
                .content(request.getContent())
                .thumbnail(request.getThumbnail())
                .published(request.getPublished())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Set<Tag> tags = new HashSet<>();
        for (int i = 0; i < request.getTags().length; i++) {
            Tag tag = tagRepository.getReferenceById(request.getTags()[i].toString());
            tags.add(tag);
        }
        post.setTags(tags);
        Post postResponse = postRepository.save(post);
        return postResponse.getId();
    }

    public Integer updatePost(Integer id, PostUpdateRequest request) {
        Post postById = postRepository.findById(id.toString()).orElseThrow(() -> new RuntimeException("Post not found"));

        Category category = categoryRepository.getReferenceById(request.getCategoryId().toString());
        Author author = authorRepository.getReferenceById(request.getAuthorId().toString());

        Post post = Post.builder()
                .id(id)
                .title(request.getTitle())
                .subTitle(request.getSubTitle())
                .category(category)
                .author(author)
                .content(request.getContent())
                .thumbnail(request.getThumbnail())
                .published(request.getPublished())
                .createdAt(postById.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
        Set<Tag> tags = new HashSet<>();
        for (int i = 0; i < request.getTags().length; i++) {
            Tag tag = tagRepository.getReferenceById(request.getTags()[i].toString());
            tags.add(tag);
        }
        post.setTags(tags);
        postRepository.save(post);
        return post.getId();
    }

    public PostResponse getPostById(Integer id) {
        Post post = postRepository.findById(id.toString()).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostResponse.builder()
                .id(id)
                .title(post.getTitle())
                .subTitle(post.getSubTitle())
                .category(post.getCategory())
                .author(post.getAuthor())
                .content(post.getContent())
                .thumbnail(post.getThumbnail())
                .published(post.getPublished())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .tags(new ArrayList<>(post.getTags()))
                .build();
    }
    public PostResponse[] getPosts(Integer page, Integer size,String categoryId) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<Post> listPost = categoryId == null ?
                postRepository.findPostsByPublished(true,pageable) :
                postRepository.findPostsByCategory_IdAndPublished(Integer.parseInt(categoryId),true, pageable);
        Post[] posts = listPost.getContent().toArray(new Post[listPost.getContent().size()]);
        PostResponse[] postResponses = new PostResponse[listPost.getContent().size()];
        for (int i = 0; i < posts.length; i++) {
            Post post = posts[i];
            PostResponse postResponse = PostResponse.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .subTitle(post.getSubTitle())
                    .category(post.getCategory())
                    .content(post.getContent())
                    .thumbnail(post.getThumbnail())
                    .published(post.getPublished())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .author(post.getAuthor())
                    .tags(new ArrayList<>(post.getTags()))
                    .build();
            postResponses[i] = postResponse;
        }
        return postResponses;
    }

    public void deletePost(Integer id){
        postRepository.deleteById(id.toString());
    }
}
