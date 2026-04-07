package app.user.admin.mapper;

import app.user.admin.dto.AdminRequestDto;
import app.user.admin.dto.AdminResponseDto;
import app.user.admin.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "isVerified", target = "verified")
    AdminResponseDto toDto(Admin admin);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "verified", target = "isVerified")
    Admin toEntity(AdminRequestDto dto);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "verified", target = "isVerified")
    void updateEntity(AdminRequestDto dto, @MappingTarget Admin admin);
}