package app.user.tenant.controller;

import app.user.tenant.dto.TenantRequestDto;
import app.user.tenant.dto.TenantResponseDto;
import app.user.tenant.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<TenantResponseDto> create(@Valid @RequestBody TenantRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponseDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(tenantService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<TenantResponseDto>> getAll() {
        return ResponseEntity.ok(tenantService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantResponseDto> update(
            @PathVariable UUID id,
            @Valid @RequestBody TenantRequestDto request) {
        return ResponseEntity.ok(tenantService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        tenantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<TenantResponseDto> activate(@PathVariable UUID id) {
        return ResponseEntity.ok(tenantService.activate(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<TenantResponseDto> deactivate(@PathVariable UUID id) {
        return ResponseEntity.ok(tenantService.deactivate(id));
    }
}