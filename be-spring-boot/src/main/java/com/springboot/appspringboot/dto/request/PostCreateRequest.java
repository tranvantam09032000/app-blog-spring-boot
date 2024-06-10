package com.springboot.appspringboot.dto.request;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostCreateRequest {
    Integer authorId;
    Integer categoryId;
    String title;
    String subTitle;
    String content;
    String thumbnail;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Boolean published;
    Integer[] tags;
}
