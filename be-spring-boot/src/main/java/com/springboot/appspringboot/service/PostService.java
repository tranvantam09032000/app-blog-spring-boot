package com.springboot.appspringboot.service;

import com.springboot.appspringboot.dto.request.*;
import com.springboot.appspringboot.dto.response.PostResponse;
import com.springboot.appspringboot.entity.*;
import com.springboot.appspringboot.mapper.PostMapper;
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
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostMapper postMapper;

    public Integer createPost(PostRequestDTO request) {

        Post post = postMapper.createCarPartDTO(request, authorRepository, categoryRepository, tagRepository);
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());

        Post postResponse = postRepository.save(post);
        return postResponse.getId();
    }

    public Integer updatePost(Integer id, PostRequestDTO request) {
        Post postById = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        Post post = postMapper.createCarPartDTO(request, authorRepository, categoryRepository, tagRepository);
        post.setCreatedAt(postById.getCreatedAt());
        post.setUpdatedAt(new Date());
        postRepository.save(post);
        return post.getId();
    }

    public PostResponse getPostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
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

    public PostResponse[] getPosts(Integer page, Integer size, String categoryId) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<Post> listPost = categoryId == null ?
                postRepository.findPostsByPublished(true, pageable) :
                postRepository.findPostsByCategory_IdAndPublished(Integer.parseInt(categoryId), true, pageable);
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

    public void deletePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.getTags().removeAll(post.getTags());
        postRepository.deleteById(id);
    }
}
