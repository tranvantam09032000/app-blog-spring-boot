package com.springboot.appspringboot.repository;

import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByPost_Id(Integer postId, Sort sort);
}
