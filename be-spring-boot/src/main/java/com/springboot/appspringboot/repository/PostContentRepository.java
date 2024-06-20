package com.springboot.appspringboot.repository;
import com.springboot.appspringboot.entity.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostContentRepository extends JpaRepository<PostContent, Integer> {
    List<PostContent> findAllByPost_Id(Integer postId);
}
