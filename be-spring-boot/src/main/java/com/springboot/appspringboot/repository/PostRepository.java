package com.springboot.appspringboot.repository;

import com.springboot.appspringboot.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findPostsByPublished(Boolean published, Pageable pageable);

    Page<Post> findPostsByCategory_IdAndPublished(Integer categoryId, Boolean published, Pageable pageable);
}
