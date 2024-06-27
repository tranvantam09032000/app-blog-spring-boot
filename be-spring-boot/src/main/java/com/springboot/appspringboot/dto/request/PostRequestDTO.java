package com.springboot.appspringboot.dto.request;
import com.springboot.appspringboot.entity.PostContent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequestDTO {
    Integer id;
    Integer authorId;
    Integer categoryId;
    String title;
    String subTitle;
    String thumbnail;
    Date createdAt;
    Date updatedAt;
    Boolean published;
    Integer[] tags;
    PostContent[] contents;
}
