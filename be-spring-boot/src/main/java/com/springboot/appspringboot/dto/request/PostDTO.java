package com.springboot.appspringboot.dto.request;

import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class PostDTO {
    Integer id;
    Author author;
    Category category;
    String title;
    String subTitle;
    String content;
    String thumbnail;
    Date createdAt;
    Date updatedAt;
    Boolean published;
    Set<Tag> tags;
}
