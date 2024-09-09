package com.springboot.appspringboot.service;

import com.springboot.appspringboot.dto.request.TagRequest;
import com.springboot.appspringboot.mapper.TagMapper;
import com.springboot.appspringboot.model.Tag;
import com.springboot.appspringboot.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class TagService {
    private TagRepository tagRepository;
    private TagMapper tagMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public Tag createTag(TagRequest request) {
        Tag tag = tagMapper.toTag(request);
        try {
            return tagRepository.save(tag);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Tag already exists");
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Tag getTagById(Long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        Tag tag = optionalTag.orElseThrow(() -> new NoSuchElementException("No tag found"));
        return tag;
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Tag updateTag(Long id, Tag request) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
        tagMapper.updateTag(tag, request);
        return tagRepository.save(tag);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTag(Long id) {tagRepository.deleteById(id);
    }
}
