package app.user.tenant.dto;

import app.user.model.Role;
import app.user.tenant.model.TenantType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TenantRequestDto {

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be between 3 and 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one digit, one lowercase, "
            +
            "one uppercase, and one special character")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9+\\-() ]{6,20}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    private Role role;

    private TenantType profileType;
    private String bio;

    private boolean active;
    private boolean verified;
}