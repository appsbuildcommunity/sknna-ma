// package app.user.admin.service;

// import app.user.admin.dto.AdminRequestDto;
// import app.user.admin.dto.AdminResponseDto;
// import app.user.admin.mapper.AdminMapper;
// import app.user.admin.model.Admin;
// import app.user.admin.repository.AdminRepository;
// import jakarta.persistence.EntityNotFoundException;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.UUID;

// @Service
// @RequiredArgsConstructor
// @Transactional
// public class UserServiceImpl implements UserService {

//     private final AdminRepository adminRepository;
//     private final AdminMapper adminMapper;
//     private final PasswordEncoder passwordEncoder;

//     @Override
//     public AdminResponseDto create(AdminRequestDto dto) {
//         if (adminRepository.existsByEmail(dto.getEmail())) {
//             throw new IllegalArgumentException("Email already in use: " + dto.getEmail());
//         }
//         Admin admin = adminMapper.toEntity(dto);
//         admin.setPassword(passwordEncoder.encode(dto.getPassword()));
//         return adminMapper.toDto(adminRepository.save(admin));
//     }

//     @Override
//     @Transactional(readOnly = true)
//     public AdminResponseDto getById(UUID id) {
//         return adminMapper.toDto(findOrThrow(id));
//     }

//     @Override
//     @Transactional(readOnly = true)
//     public List<AdminResponseDto> getAll() {
//         return adminRepository.findAll()
//                 .stream()
//                 .map(adminMapper::toDto)
//                 .toList();
//     }

//     @Override
//     public AdminResponseDto update(UUID id, AdminRequestDto dto) {
//         Admin admin = findOrThrow(id);
//         adminMapper.updateEntity(dto, admin);
//         if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
//             admin.setPassword(passwordEncoder.encode(dto.getPassword()));
//         }
//         return adminMapper.toDto(adminRepository.save(admin));
//     }

//     @Override
//     public void delete(UUID id) {
//         if (!adminRepository.existsById(id)) {
//             throw new EntityNotFoundException("Admin not found with id: " + id);
//         }
//         adminRepository.deleteById(id);
//     }

//     private Admin findOrThrow(UUID id) {
//         return adminRepository.findById(id)
//                 .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
//     }
// }