package app.user.tenant.dto;

import app.user.model.Role;
import app.user.tenant.model.TenantType;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TenantResponseDto {
    UUID id;
    String fullName;
    String email;
    String phoneNumber;
    Role role;
    TenantType profileType;
    String bio;
    boolean active;
    boolean verified;
    LocalDate createdAt;
    LocalDate updatedAt;
}