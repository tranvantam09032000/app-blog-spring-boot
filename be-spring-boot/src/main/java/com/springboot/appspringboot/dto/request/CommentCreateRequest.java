package com.springboot.appspringboot.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.appspringboot.entity.Post;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentCreateRequest {
    String fullName;
    String content;
    Date createdAt;
    Date updatedAt;
    Integer postId;
}
