package com.springboot.appspringboot.dto.request;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorRequestDTO {
    Integer id;
    String email;
    String password;
    String firstName;
    String lastName;
    Date createdAt;
    Date updatedAt;
}
