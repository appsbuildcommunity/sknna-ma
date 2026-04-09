package app.user.admin.service;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.user.admin.dto.AdminUpdateUserDto;
import app.user.admin.dto.AdminUserResponseDTO;
import app.user.admin.mapper.AdminUserMapper;
import app.user.admin.model.Admin;
import app.user.admin.repository.AdminRepository;
import app.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserService
        extends GenericAdminService<Admin, UUID, AdminUserResponseDTO, AdminUpdateUserDto> {

    private final AdminRepository adminRepository;
    private final AdminUserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected JpaRepository<Admin, UUID> getRepository() {
        return adminRepository;
    }

    @Override
    protected AdminUserResponseDTO toResponseDTO(Admin admin) {
        return mapper.toDto(admin);
    }

    @Override
    protected void applyUpdate(Admin admin, AdminUpdateUserDto dto) {
        mapper.updateEntity(dto, admin);
    }

    public void updatePassword(UUID id, String newPassword) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        admin.setPassword(passwordEncoder.encode(newPassword));
        adminRepository.save(admin);
    }

    @Override
    protected void deactivate(Admin admin) {
        admin.setIsActive(false);
    }
}