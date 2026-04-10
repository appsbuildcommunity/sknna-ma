package app.user.admin.service;

import app.common.exception.ResourceNotFoundException;
import app.common.exception.BusinessException;
import app.user.admin.dto.AdminUserRequestDto;
import app.user.admin.dto.AdminUserResponseDTO;
import app.user.admin.mapper.AdminMapper;
import app.user.admin.model.Admin;
import app.user.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminUserResponseDTO create(AdminUserRequestDto request) {
        if (adminRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("EMAIL_TAKEN", "Email is already in use");
        }
        Admin admin = adminMapper.toEntity(request);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        return adminMapper.toResponseDto(adminRepository.save(admin));
    }

    @Override
    public AdminUserResponseDTO getOne(UUID id) {
        return adminMapper.toResponseDto(findOrThrow(id));
    }

    @Override
    public List<AdminUserResponseDTO> getAll() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toResponseDto)
                .toList();
    }

    @Override
    public AdminUserResponseDTO update(UUID id, AdminUserRequestDto request) {
        Admin admin = findOrThrow(id);

        if (!admin.getEmail().equals(request.getEmail())
                && adminRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("EMAIL_TAKEN", "Email is already in use");
        }

        admin.setFullName(request.getFullName());
        admin.setEmail(request.getEmail());
        admin.setPhone(request.getPhoneNumber());
        admin.setRole(request.getRole());
        admin.setIsActive(request.isActive());
        admin.setIsVerified(request.isVerified());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return adminMapper.toResponseDto(adminRepository.save(admin));
    }

    @Override
    public void delete(UUID id) {
        if (!adminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }

    @Override
    public AdminUserResponseDTO activate(UUID id) {
        Admin admin = findOrThrow(id);
        admin.setIsActive(true);
        return adminMapper.toResponseDto(adminRepository.save(admin));
    }

    @Override
    public AdminUserResponseDTO deactivate(UUID id) {
        Admin admin = findOrThrow(id);
        admin.setIsActive(false);
        return adminMapper.toResponseDto(adminRepository.save(admin));
    }

    // ── private ──────────────────────────────────────────────────────────────

    private Admin findOrThrow(UUID id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
    }
}
