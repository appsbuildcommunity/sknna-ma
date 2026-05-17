package app.common.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import app.user.tenant.dto.TenantRequestDto;
import app.user.tenant.dto.TenantResponseDto;
import app.user.tenant.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final TenantService tenantService;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        return "Welcome to the seknna project! `"
                + request.getSession().getId() + "`";
    }

    @PostMapping("/signup")
    public ResponseEntity<TenantResponseDto> signup(@Valid @RequestBody TenantRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantService.create(request));
    }
}