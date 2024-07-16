package com.springboot.appspringboot.mapper;
import com.springboot.appspringboot.dto.request.AuthorRequestDTO;
import com.springboot.appspringboot.dto.request.CommentRequestDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Comment;
import com.springboot.appspringboot.entity.Post;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AuthorMapper {

    @Mapping(target = "password", source = "password")
    @Mapping(target = "createdAt", source = "authorRequestDTO.createdAt")
    @Mapping(target = "updatedAt", source = "authorRequestDTO.updatedAt")
    @Mapping(target = "id", source = "authorRequestDTO.id")
    Author authorRequestToAuthor(AuthorRequestDTO authorRequestDTO, String password);
}
