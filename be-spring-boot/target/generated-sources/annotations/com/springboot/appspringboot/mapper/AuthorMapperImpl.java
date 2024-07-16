package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.AuthorRequestDTO;
import com.springboot.appspringboot.entity.Author;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author authorRequestToAuthor(AuthorRequestDTO authorRequestDTO, String password) {
        if ( authorRequestDTO == null && password == null ) {
            return null;
        }

        Author author = new Author();

        if ( authorRequestDTO != null ) {
            author.setCreatedAt( authorRequestDTO.getCreatedAt() );
            author.setUpdatedAt( authorRequestDTO.getUpdatedAt() );
            author.setId( authorRequestDTO.getId() );
            author.setEmail( authorRequestDTO.getEmail() );
            author.setFirstName( authorRequestDTO.getFirstName() );
            author.setLastName( authorRequestDTO.getLastName() );
        }
        author.setPassword( password );

        return author;
    }
}
