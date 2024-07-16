package com.springboot.appspringboot.dto.request;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikePostRequestDTO {
    Integer id;
    Integer authorId;
    Integer postId;
    Date createdAt;
    Date updatedAt;
}