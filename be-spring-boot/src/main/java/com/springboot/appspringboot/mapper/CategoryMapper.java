package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.CategoryRequest;
import com.springboot.appspringboot.dto.response.CategoryResponse;
import com.springboot.appspringboot.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateCategory(@MappingTarget Category category, Category request);
}
