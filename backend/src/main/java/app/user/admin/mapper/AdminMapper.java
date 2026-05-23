package app.user.admin.mapper;

import app.user.admin.dto.AdminUserRequestDto;
import app.user.admin.dto.AdminUserResponseDTO;
import app.user.admin.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "verified", target = "verified")
    @Mapping(source = "active", target = "active")
    AdminUserResponseDTO toResponseDto(Admin admin);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    Admin toEntity(AdminUserRequestDto dto);
}