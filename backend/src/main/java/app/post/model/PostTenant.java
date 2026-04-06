package app.post.model;

import app.group.model.GroupSearchProperties;
import app.property.model.Property;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "post_tenants")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostTenant extends Post {

    // UUID brut vers Tenant — auteur
    @Column(nullable = false, updatable = false)
    private UUID tenantId;

    // Property optionnelle — null si c'est une question/proposition générale
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = true)
    @ToString.Exclude
    private Property property;

    // Groupe optionnel — null si c'est une publication publique
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = true)
    @ToString.Exclude
    private GroupSearchProperties group;

    @Column(precision = 10, scale = 2)
    private BigDecimal budgetMax;

    private LocalDate moveInDate;
}