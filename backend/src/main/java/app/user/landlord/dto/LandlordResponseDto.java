package app.user.landlord.dto;

import app.user.model.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class LandlordResponseDto {
    UUID id;
    String fullName;
    String email;
    String phoneNumber;
    Role role;
    String bio;
    boolean active;
    boolean verified;
    LocalDate createdAt;
    LocalDate updatedAt;
}