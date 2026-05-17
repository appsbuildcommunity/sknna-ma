package app.user.dto;

import app.user.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    @Size(max = 100)
    private String fullName;

    @Email @NotBlank
    private String email;

    @NotBlank
    @Size(min = 4)
    private String password;

    private String phoneNumber;

    private Role role;
}