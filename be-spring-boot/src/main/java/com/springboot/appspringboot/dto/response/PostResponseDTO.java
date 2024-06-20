package com.springboot.appspringboot.dto.response;

import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.PostContent;
import com.springboot.appspringboot.entity.Tag;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponseDTO {
    Integer id;
    String title;
    String subTitle;
    String thumbnail;
    Date createdAt;
    Date updatedAt;
    Boolean published;
    List<Tag> tags;
    List<PostContent> contents;
    Author author;
    Category category;
}
