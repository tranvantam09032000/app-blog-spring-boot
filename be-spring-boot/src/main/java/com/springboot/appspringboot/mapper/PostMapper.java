package com.springboot.appspringboot.mapper;
import com.springboot.appspringboot.dto.request.PostDTO;
import com.springboot.appspringboot.dto.request.PostRequestDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.entity.Category;
import com.springboot.appspringboot.entity.Post;
import com.springboot.appspringboot.entity.Tag;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", qualifiedByName = "mapdemo")
    @Mapping(target = "category", qualifiedByName = "mapdemo")
    @Mapping(target = "tags", qualifiedByName = "mapdemo")
    Post createCarPartDTO(PostRequestDTO postRequestDTO,
                          @MappingTarget Post post,
                          @Context Set<Tag> tags,
                          @Context Author author,
                          @Context Category category);


}
