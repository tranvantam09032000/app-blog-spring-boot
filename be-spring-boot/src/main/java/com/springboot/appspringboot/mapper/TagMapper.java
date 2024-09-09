package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.TagRequest;
import com.springboot.appspringboot.dto.response.TagResponse;
import com.springboot.appspringboot.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toTag(TagRequest tagRequest);
    TagResponse toTagResponse(Tag tag);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateTag(@MappingTarget Tag tag, Tag request);
}
