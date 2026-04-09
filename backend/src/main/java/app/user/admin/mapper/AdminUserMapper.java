package app.user.admin.mapper;

import app.user.admin.dto.AdminUpdateUserDto;
import app.user.admin.dto.AdminUserRequestDto;
import app.user.admin.dto.AdminUserResponseDTO;
import app.user.admin.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdminUserMapper {

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "isVerified", target = "verified")
    AdminUserResponseDTO toDto(Admin admin);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "verified", target = "isVerified")
    Admin toEntity(AdminUserRequestDto dto);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "verified", target = "isVerified")
    void updateEntity(AdminUpdateUserDto dto, @MappingTarget Admin admin);
}