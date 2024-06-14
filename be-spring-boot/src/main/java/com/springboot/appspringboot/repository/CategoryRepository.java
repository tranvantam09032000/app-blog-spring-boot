package com.springboot.appspringboot.repository;

import com.springboot.appspringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
