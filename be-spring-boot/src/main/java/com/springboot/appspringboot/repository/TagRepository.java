package com.springboot.appspringboot.repository;

import com.springboot.appspringboot.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}
