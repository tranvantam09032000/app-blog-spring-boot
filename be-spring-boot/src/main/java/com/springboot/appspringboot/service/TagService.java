package com.springboot.appspringboot.service;
import com.springboot.appspringboot.entity.Tag;
import com.springboot.appspringboot.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTags() {
        List<Tag> listTag = tagRepository.findAll();
        return listTag;
    }
}
