package com.springboot.appspringboot.service;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id.toString()).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
