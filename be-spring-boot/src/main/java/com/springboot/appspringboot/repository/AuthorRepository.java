package com.springboot.appspringboot.repository;

import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
