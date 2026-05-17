package app.common.controllers;

import app.user.dto.LoginRequest;
import app.user.dto.LoginResponse;
import app.user.service.AuthService;
import app.user.tenant.dto.TenantRequestDto;
import app.user.tenant.dto.TenantResponseDto;
import app.user.tenant.service.TenantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final TenantService tenantService;
    private final AuthService authService;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        return "Welcome to the seknna project! `"
                + request.getSession().getId() + "`";
    }

    @PostMapping("/signup")
    public ResponseEntity<TenantResponseDto> signup(@Valid @RequestBody TenantRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantService.create(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully.");
    }
}