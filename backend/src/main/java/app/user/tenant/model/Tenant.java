package app.user.tenant.model;

import app.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tenants")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Tenant extends User {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TenantType profileType; // STUDENT, YOUNG_PROFESSIONAL

    @Column(columnDefinition = "TEXT")
    private String bio;
}