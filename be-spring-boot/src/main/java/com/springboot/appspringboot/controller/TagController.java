package com.springboot.appspringboot.controller;

import com.springboot.appspringboot.dto.request.TagRequest;
import com.springboot.appspringboot.dto.response.TagResponse;
import com.springboot.appspringboot.mapper.TagMapper;
import com.springboot.appspringboot.model.Tag;
import com.springboot.appspringboot.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class TagController {

    private TagService tagService;
    private TagMapper tagMapper;
    @PostMapping
    public ResponseEntity<TagResponse> createTag(@Valid @RequestBody TagRequest request) {
        Tag tag = tagService.createTag(request);

        return ResponseEntity.ok(tagMapper.toTagResponse(tag));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable Long id) {
        Tag tag = tagService.getTagById(id);
        return ResponseEntity.ok(tagMapper.toTagResponse(tag));
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<TagResponse> tags = tagService.getAllTags().stream().map(tag->tagMapper.toTagResponse(tag)).toList();
        return ResponseEntity.ok(tags);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> updateTag(@PathVariable Long id, @Valid @RequestBody Tag request) {
        Tag tag = tagService.updateTag(id, request);
        return ResponseEntity.ok(tagMapper.toTagResponse(tag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
