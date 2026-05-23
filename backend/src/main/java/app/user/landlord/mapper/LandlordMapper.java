package app.user.landlord.mapper;

import app.user.landlord.dto.LandlordRequestDto;
import app.user.landlord.dto.LandlordResponseDto;
import app.user.landlord.model.Landlord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LandlordMapper {

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "verified", target = "verified")
    LandlordResponseDto toResponseDto(Landlord landlord);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    Landlord toEntity(LandlordRequestDto dto);
}