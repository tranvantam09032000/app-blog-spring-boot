package com.springboot.appspringboot.controller;

import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Tag;
import com.springboot.appspringboot.service.AuthorService;
import com.springboot.appspringboot.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @CrossOrigin
    @GetMapping()
    List<Tag> getTags(){
        return tagService.getTags();
    }

}