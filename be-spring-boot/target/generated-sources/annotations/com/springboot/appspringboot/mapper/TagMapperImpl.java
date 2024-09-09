package com.springboot.appspringboot.mapper;

import com.springboot.appspringboot.dto.request.TagRequest;
import com.springboot.appspringboot.dto.response.TagResponse;
import com.springboot.appspringboot.model.Tag;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag toTag(TagRequest tagRequest) {
        if ( tagRequest == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setName( tagRequest.getName() );

        return tag;
    }

    @Override
    public TagResponse toTagResponse(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagResponse tagResponse = new TagResponse();

        tagResponse.setId( tag.getId() );
        tagResponse.setName( tag.getName() );
        tagResponse.setCreatedAt( tag.getCreatedAt() );
        tagResponse.setUpdatedAt( tag.getUpdatedAt() );

        return tagResponse;
    }

    @Override
    public void updateTag(Tag tag, Tag request) {
        if ( request == null ) {
            return;
        }

        tag.setName( request.getName() );
    }
}
