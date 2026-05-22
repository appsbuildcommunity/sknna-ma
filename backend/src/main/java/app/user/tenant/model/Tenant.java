package app.user.tenant.model;

import app.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tenants")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Tenant extends User {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TenantType profileType;

    @Column(columnDefinition = "TEXT")
    private String bio;

    protected Tenant() {
        super();
    }
}