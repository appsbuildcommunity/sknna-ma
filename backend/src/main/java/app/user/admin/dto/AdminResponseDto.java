package app.user.admin.dto;

import java.time.LocalDate;
import java.util.UUID;

import app.user.model.Role;
import lombok.Data;

@Data
public class AdminResponseDto {
    UUID id;
    String fullName;
    String email;
    String phoneNumber;
    Role role;
    boolean isActive;
    boolean verified;
    LocalDate createdAt;
    LocalDate updatedAt;
}
