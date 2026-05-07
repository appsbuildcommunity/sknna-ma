package app.user.landlord.service;

import app.common.exception.ResourceNotFoundException;
import app.common.exception.BusinessException;
import app.user.landlord.dto.LandlordRequestDto;
import app.user.landlord.dto.LandlordResponseDto;
import app.user.landlord.mapper.LandlordMapper;
import app.user.landlord.model.Landlord;
import app.user.landlord.repository.LandlordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LandlordServiceImpl implements LandlordService {

    private final LandlordRepository landlordRepository;
    private final LandlordMapper landlordMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LandlordResponseDto create(LandlordRequestDto request) {
        if (landlordRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("EMAIL_TAKEN", "Email is already in use");
        }
        Landlord landlord = landlordMapper.toEntity(request);
        landlord.setPassword(passwordEncoder.encode(request.getPassword()));
        return landlordMapper.toResponseDto(landlordRepository.save(landlord));
    }

    @Override
    public LandlordResponseDto getOne(UUID id) {
        return landlordMapper.toResponseDto(findOrThrow(id));
    }

    @Override
    public List<LandlordResponseDto> getAll() {
        return landlordRepository.findAll()
                .stream()
                .filter(landlord -> landlord.getRole() == app.user.model.Role.landlord)
                .map(landlordMapper::toResponseDto)
                .toList();
    }

    @Override
    public LandlordResponseDto update(UUID id, LandlordRequestDto request) {
        Landlord landlord = findOrThrow(id);

        if (!landlord.getEmail().equals(request.getEmail())
                && landlordRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("EMAIL_TAKEN", "Email is already in use");
        }

        landlord.setFullName(request.getFullName());
        landlord.setEmail(request.getEmail());
        landlord.setPhone(request.getPhoneNumber());
        landlord.setRole(request.getRole());
        landlord.setBio(request.getBio());
        landlord.setIsActive(request.isActive());
        landlord.setIsVerified(request.isVerified());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            landlord.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return landlordMapper.toResponseDto(landlordRepository.save(landlord));
    }

    @Override
    public void delete(UUID id) {
        if (!landlordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Landlord not found with id: " + id);
        }
        landlordRepository.deleteById(id);
    }

    @Override
    public LandlordResponseDto activate(UUID id) {
        Landlord landlord = findOrThrow(id);
        landlord.setIsActive(true);
        return landlordMapper.toResponseDto(landlordRepository.save(landlord));
    }

    @Override
    public LandlordResponseDto deactivate(UUID id) {
        Landlord landlord = findOrThrow(id);
        landlord.setIsActive(false);
        return landlordMapper.toResponseDto(landlordRepository.save(landlord));
    }

    // ── private ──────────────────────────────────────────────────────────────

    private Landlord findOrThrow(UUID id) {
        return landlordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Landlord not found with id: " + id));
    }
}