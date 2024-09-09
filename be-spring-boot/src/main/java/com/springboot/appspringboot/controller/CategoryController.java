package com.springboot.appspringboot.controller;

import com.springboot.appspringboot.dto.request.CategoryRequest;
import com.springboot.appspringboot.dto.response.CategoryResponse;
import com.springboot.appspringboot.mapper.CategoryMapper;
import com.springboot.appspringboot.model.Category;
import com.springboot.appspringboot.service.CategoryService;
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
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        Category category = categoryService.createCategory(request);

        return ResponseEntity.ok(categoryMapper.toCategoryResponse(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryMapper.toCategoryResponse(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories().stream().map(category->categoryMapper.toCategoryResponse(category)).toList();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody Category request) {
        Category category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(categoryMapper.toCategoryResponse(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
