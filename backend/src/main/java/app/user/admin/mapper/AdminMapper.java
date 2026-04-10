package app.user.admin.mapper;

import app.user.admin.dto.AdminUserRequestDto;
import app.user.admin.dto.AdminUserResponseDTO;
import app.user.admin.model.Admin;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "isVerified", target = "verified")
    AdminUserResponseDTO toResponseDto(Admin admin);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "verified", target = "isVerified")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true) // handled manually (encoded)
    Admin toEntity(AdminUserRequestDto dto);
}