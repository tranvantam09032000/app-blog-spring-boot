package com.springboot.appspringboot.controller;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @CrossOrigin
    @GetMapping()
    List<Author> getAuthor() {
        return authorService.getAuthor();
    }

}