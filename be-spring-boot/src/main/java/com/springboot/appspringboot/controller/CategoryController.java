package com.springboot.appspringboot.controller;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @CrossOrigin
    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @CrossOrigin
    @GetMapping()
    List<Category> getCategories() {
        return categoryService.getCategories();
    }

}