package com.springboot.appspringboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "comment")
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String fullName;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "postId")
    @JsonBackReference
    Post post;
}
