package app.user.tenant.service;

import app.common.exception.ResourceNotFoundException;
import app.common.exception.BusinessException;
import app.user.tenant.dto.TenantRequestDto;
import app.user.tenant.dto.TenantResponseDto;
import app.user.tenant.mapper.TenantMapper;
import app.user.tenant.model.Tenant;
import app.user.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import app.user.tenant.dto.TenantUpdateDto;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TenantResponseDto create(TenantRequestDto request) {
        if (tenantRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("EMAIL_TAKEN", "Email is already in use");
        }
        Tenant tenant = tenantMapper.toEntity(request);
        tenant.setPassword(passwordEncoder.encode(request.getPassword()));
        tenant.setActive(true); // always set server-side
        tenant.setVerified(false); // always set server-side
        return tenantMapper.toResponseDto(tenantRepository.save(tenant));
    }

    @Override
    public TenantResponseDto getOne(UUID id) {
        return tenantMapper.toResponseDto(findOrThrow(id));
    }

    @Override
    public List<TenantResponseDto> getAll() {
        return tenantRepository.findAll()
                .stream()
                .filter(tenant -> tenant.getRole() == app.user.model.Role.tenant)
                .map(tenantMapper::toResponseDto)
                .toList();
    }

    @Override
    public TenantResponseDto update(UUID id, TenantRequestDto request) {
        Tenant tenant = findOrThrow(id);

        if (!tenant.getEmail().equals(request.getEmail())
                && tenantRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("EMAIL_TAKEN", "Email is already in use");
        }

        tenant.setFullName(request.getFullName());
        tenant.setEmail(request.getEmail());
        tenant.setPhone(request.getPhoneNumber());
        tenant.setRole(request.getRole());
        tenant.setProfileType(request.getProfileType());
        tenant.setBio(request.getBio());
        tenant.setActive(request.isActive()); // was setIsActive
        tenant.setVerified(request.isVerified()); // was setIsVerified

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            tenant.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return tenantMapper.toResponseDto(tenantRepository.save(tenant));
    }

    @Override
    public TenantResponseDto patch(UUID id, TenantUpdateDto request) {
        Tenant tenant = findOrThrow(id);

        if (request.getFullName() != null)
            tenant.setFullName(request.getFullName());

        if (request.getEmail() != null && !request.getEmail().equals(tenant.getEmail())) {
            if (tenantRepository.existsByEmail(request.getEmail())) {
                throw new BusinessException("EMAIL_TAKEN", "Email is already in use");
            }
            tenant.setEmail(request.getEmail());
        }

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            tenant.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getPhoneNumber() != null)
            tenant.setPhone(request.getPhoneNumber());
        if (request.getRole() != null)
            tenant.setRole(request.getRole());
        if (request.getProfileType() != null)
            tenant.setProfileType(request.getProfileType());
        if (request.getBio() != null)
            tenant.setBio(request.getBio());
        if (request.getActive() != null)
            tenant.setActive(request.getActive()); // was setIsActive
        if (request.getVerified() != null)
            tenant.setVerified(request.getVerified()); // was setIsVerified

        return tenantMapper.toResponseDto(tenantRepository.save(tenant));
    }

    @Override
    public void delete(UUID id) {
        if (!tenantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tenant not found with id: " + id);
        }
        tenantRepository.deleteById(id);
    }

    @Override
    public TenantResponseDto activate(UUID id) {
        Tenant tenant = findOrThrow(id);
        tenant.setActive(true);
        return tenantMapper.toResponseDto(tenantRepository.save(tenant));
    }

    @Override
    public TenantResponseDto deactivate(UUID id) {
        Tenant tenant = findOrThrow(id);
        tenant.setActive(false);
        return tenantMapper.toResponseDto(tenantRepository.save(tenant));
    }

    // ── private ──────────────────────────────────────────────────────────────

    private Tenant findOrThrow(UUID id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + id));
    }
}