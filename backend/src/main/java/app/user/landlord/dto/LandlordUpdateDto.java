package app.user.landlord.dto;

import app.user.model.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LandlordUpdateDto {

    @Size(max = 100)
    private String fullName;

    @Email
    private String email;

    @Size(min = 4)
    private String password;

    @Pattern(regexp = "^[0-9+\\-() ]{6,20}$")
    private String phoneNumber;

    @Size(max = 200)
    private String bio;

    private Role role;

    private Boolean active;
    private Boolean verified;
}