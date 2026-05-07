package app.user.tenant.mapper;

import app.user.tenant.dto.TenantRequestDto;
import app.user.tenant.dto.TenantResponseDto;
import app.user.tenant.model.Tenant;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "isVerified", target = "verified")
    TenantResponseDto toResponseDto(Tenant tenant);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "verified", target = "isVerified")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    Tenant toEntity(TenantRequestDto dto);
}