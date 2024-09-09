package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.UserRequest;
import com.springboot.appspringboot.dto.response.UserResponse;
import com.springboot.appspringboot.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( userRequest.getEmail() );
        user.setPassword( userRequest.getPassword() );
        user.setFullName( userRequest.getFullName() );
        user.setRole( userRequest.getRole() );

        return user;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setFullName( user.getFullName() );
        userResponse.setRole( user.getRole() );
        userResponse.setCreatedAt( user.getCreatedAt() );
        userResponse.setUpdatedAt( user.getUpdatedAt() );

        return userResponse;
    }

    @Override
    public void updateUser(User user, User request) {
        if ( request == null ) {
            return;
        }

        user.setPassword( request.getPassword() );
        user.setFullName( request.getFullName() );
        user.setRole( request.getRole() );
    }
}
