package app.user.admin.dto;

import app.user.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminUpdateUserDto {
    @Size(max = 100)
    private String fullName;       // optional — null means "don't change"

    @Email
    private String email;          // optional

    @Size(min = 4)
    private String password;       // optional

    @Pattern(regexp = "^[0-9+\\-() ]{6,20}$")
    private String phoneNumber;    // optional

    private Role role;             // optional

    private Boolean active;      // Boolean (not boolean) so null = untouched
    private Boolean verified;      // same here    
}
