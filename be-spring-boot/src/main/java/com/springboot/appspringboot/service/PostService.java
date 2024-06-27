package com.springboot.appspringboot.service;

import com.springboot.appspringboot.dto.request.*;
import com.springboot.appspringboot.dto.response.PostResponseDTO;
import com.springboot.appspringboot.entity.*;
import com.springboot.appspringboot.mapper.PostMapper;
import com.springboot.appspringboot.repository.AuthorRepository;
import com.springboot.appspringboot.repository.CategoryRepository;
import com.springboot.appspringboot.repository.PostContentRepository;
import com.springboot.appspringboot.repository.PostRepository;
import com.springboot.appspringboot.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private PostContentRepository postContentRepository;

    public Integer createPost(PostRequestDTO request) {
        Author author = authorRepository.getReferenceById(request.getAuthorId());
        Category category = categoryRepository.getReferenceById(request.getCategoryId());
        List<Tag> listTag = tagRepository.findAllById(List.of(request.getTags()));
        Set<Tag> tags = new HashSet<>(listTag);

        Post post = postMapper.postRequestToPost(request, author, category, tags);
        Post postResponse = postRepository.save(post);
        return postResponse.getId();
    }

    public Integer updatePost(Integer id, PostRequestDTO request) {
        Post postById = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        Author author = authorRepository.getReferenceById(request.getAuthorId());
        Category category = categoryRepository.getReferenceById(request.getCategoryId());
        List<Tag> listTag = tagRepository.findAllById(List.of(request.getTags()));
        Set<Tag> tags = new HashSet<>(listTag);

        Post post = postMapper.postRequestToPost(request, author, category, tags);
        postRepository.save(post);
        return post.getId();
    }

    public PostResponseDTO getPostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return postMapper.postToPostResponseDTO(post);
    }

    public PostResponseDTO[] getPosts(Integer page, Integer size, String categoryId) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<Post> listPost = categoryId == null ?
                postRepository.findPostsByPublished(true, pageable) :
                postRepository.findPostsByCategory_IdAndPublished(Integer.parseInt(categoryId), true, pageable);
        return listPost.getContent().stream().map(post -> postMapper.postToPostResponseDTO(post)).toArray(PostResponseDTO[]::new);
    }

    public void deletePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.getTags().removeAll(post.getTags());
        postRepository.deleteById(id);
    }
}
