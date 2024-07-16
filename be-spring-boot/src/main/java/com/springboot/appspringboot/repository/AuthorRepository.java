package com.springboot.appspringboot.repository;

import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findAuthorByEmail(String email);
}
