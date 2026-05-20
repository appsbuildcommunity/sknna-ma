package app.user.landlord.mapper;

import app.user.landlord.dto.LandlordRequestDto;
import app.user.landlord.dto.LandlordResponseDto;
import app.user.landlord.model.Landlord;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LandlordMapper {

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "isVerified", target = "verified")
    @Mapping(source = "isActive", target = "active")
    LandlordResponseDto toResponseDto(Landlord landlord);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "verified", target = "isVerified")
    @Mapping(source = "active", target = "isActive")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    Landlord toEntity(LandlordRequestDto dto);
}