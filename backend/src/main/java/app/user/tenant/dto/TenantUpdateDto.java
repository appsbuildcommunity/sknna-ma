package app.user.tenant.dto;

import app.user.model.Role;
import app.user.tenant.model.TenantType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TenantUpdateDto {

    @Size(max = 100)
    private String fullName;

    @Email
    private String email;

    @Size(min = 4)
    private String password;

    @Pattern(regexp = "^[0-9+\\-() ]{6,20}$")
    private String phoneNumber;

    private Role role;
    private TenantType profileType;
    private String bio;

    private Boolean active;
    private Boolean verified;
}