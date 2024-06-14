package com.springboot.appspringboot.dto.request;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequestDTO {
    Integer authorId;
    Integer categoryId;
    String title;
    String subTitle;
    String content;
    String thumbnail;
    Date createdAt;
    Date updatedAt;
    Boolean published;
    Integer[] tags;
}
