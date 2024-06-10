package com.springboot.appspringboot.service;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthor() {
        return authorRepository.findAll();
    }
}
