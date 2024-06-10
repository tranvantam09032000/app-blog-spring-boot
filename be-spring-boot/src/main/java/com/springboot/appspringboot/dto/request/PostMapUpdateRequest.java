package com.springboot.appspringboot.dto.request;
import com.springboot.appspringboot.entity.Tag;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostMapUpdateRequest {
    Integer id;
    Integer authorId;
    Integer categoryId;
    String title;
    String subTitle;
    String content;
    String thumbnail;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Boolean published;
    Set<Tag> tags;
}
