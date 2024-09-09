package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.CategoryRequest;
import com.springboot.appspringboot.dto.response.CategoryResponse;
import com.springboot.appspringboot.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( categoryRequest.getName() );

        return category;
    }

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setName( category.getName() );
        categoryResponse.setCreatedAt( category.getCreatedAt() );
        categoryResponse.setUpdatedAt( category.getUpdatedAt() );

        return categoryResponse;
    }

    @Override
    public void updateCategory(Category category, Category request) {
        if ( request == null ) {
            return;
        }

        category.setName( request.getName() );
    }
}
