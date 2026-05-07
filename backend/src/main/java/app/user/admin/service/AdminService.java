package app.user.admin.service;

import app.user.admin.dto.AdminUserRequestDto;
import app.user.admin.dto.AdminUserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    AdminUserResponseDTO create(AdminUserRequestDto request);
    AdminUserResponseDTO getOne(UUID id);
    List<AdminUserResponseDTO> getAll();
    AdminUserResponseDTO update(UUID id, AdminUserRequestDto request);
    AdminUserResponseDTO patch(UUID id, AdminUpdateUserDto request);
    void delete(UUID id);
    AdminUserResponseDTO activate(UUID id);
    AdminUserResponseDTO deactivate(UUID id);
}