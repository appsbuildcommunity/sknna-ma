package app.user.tenant.service;

import app.user.tenant.dto.TenantRequestDto;
import app.user.tenant.dto.TenantResponseDto;
import app.user.tenant.dto.TenantUpdateDto;


import java.util.List;
import java.util.UUID;

public interface TenantService {
    TenantResponseDto create(TenantRequestDto request);
    TenantResponseDto getOne(UUID id);
    List<TenantResponseDto> getAll();
    TenantResponseDto update(UUID id, TenantRequestDto request);
    TenantResponseDto patch(UUID id, TenantUpdateDto request);
    void delete(UUID id);
    TenantResponseDto activate(UUID id);
    TenantResponseDto deactivate(UUID id);
}