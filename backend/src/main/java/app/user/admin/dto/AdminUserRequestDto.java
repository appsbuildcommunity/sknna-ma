package app.user.admin.dto;

import app.user.model.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AdminUserRequestDto {

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be between 3 and 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9+\\-() ]{6,20}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    private Role role;

    private boolean isActive;
    private boolean verified;

    // Getters and Setters
}