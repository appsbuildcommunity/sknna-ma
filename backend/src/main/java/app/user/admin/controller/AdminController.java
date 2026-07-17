package app.user.admin.controller;

import app.user.admin.dto.AdminUpdateUserDto;
import app.user.admin.dto.AdminUserRequestDto;
import app.user.admin.dto.AdminUserResponseDTO;
import app.user.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminUserResponseDTO> create(@Valid @RequestBody AdminUserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminUserResponseDTO> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<AdminUserResponseDTO>> getAll() {
        return ResponseEntity.ok(adminService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminUserResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUserRequestDto request) {
        return ResponseEntity.ok(adminService.update(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdminUserResponseDTO> patch(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUpdateUserDto request) {
        return ResponseEntity.ok(adminService.patch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<AdminUserResponseDTO> activate(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.activate(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<AdminUserResponseDTO> deactivate(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.deactivate(id));
    }
}