package com.springboot.appspringboot.service;

import com.springboot.appspringboot.dto.request.CategoryRequest;
import com.springboot.appspringboot.mapper.CategoryMapper;
import com.springboot.appspringboot.model.Category;
import com.springboot.appspringboot.repository.CategoryRepository;
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
public class CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(CategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        try {
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Category already exists");
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.orElseThrow(() -> new NoSuchElementException("No category found"));
        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Category updateCategory(Long id, Category request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryMapper.updateCategory(category, request);
        return categoryRepository.save(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(Long id) {categoryRepository.deleteById(id);
    }
}
