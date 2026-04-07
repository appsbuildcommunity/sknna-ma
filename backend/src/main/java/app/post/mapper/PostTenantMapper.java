package app.post.mapper;

import org.mapstruct.Mapper;

import app.post.dto.PostRequestDto;
import app.post.dto.PostResponseDto;
import app.post.model.PostTenant;

@Mapper(componentModel = "spring")
public interface PostTenantMapper {
    // to dto
    PostResponseDto toDto(PostTenant postTenant);

    // to entity
    PostTenant toEntity(PostRequestDto postDto);
}
