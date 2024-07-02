package com.springboot.appspringboot.repository;

import com.springboot.appspringboot.entity.LikeOfPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeOfPostRepository extends JpaRepository<LikeOfPost, Integer> {
    LikeOfPost getByPostIdAndAuthorId(Integer postId, Integer authorId);
    List<LikeOfPost> findAllByPost_Id(Integer postId);
}
