package app.user.admin.controller;

import app.user.admin.dto.AdminUpdateUserDto;
import app.user.admin.dto.AdminUserResponseDTO;
import app.user.admin.model.Admin;
import app.user.admin.service.AdminUserService;
import app.user.admin.service.GenericAdminService;
import app.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController
        extends GenericAdminController<Admin, UUID, AdminUserResponseDTO, AdminUpdateUserDto> {

    private final AdminUserService service;

    @Override
    protected GenericAdminService<Admin, UUID, AdminUserResponseDTO, AdminUpdateUserDto> getService() {
        return service;
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable UUID id,
            @RequestParam String newPassword) {
        service.updatePassword(id, newPassword);
        return ResponseEntity.noContent().build();
    }
}