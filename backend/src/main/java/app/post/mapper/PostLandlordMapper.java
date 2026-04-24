package app.post.mapper;

import org.mapstruct.Mapper;

import app.post.dto.PostRequestDto;
import app.post.dto.PostResponseDto;
import app.post.model.PostLandlord;

@Mapper(componentModel = "spring")
public interface PostLandlordMapper {
    // to dto
    PostResponseDto toDto(PostLandlord postLandlord);

    // to entity
    PostLandlord toEntity(PostRequestDto postDto);
}
