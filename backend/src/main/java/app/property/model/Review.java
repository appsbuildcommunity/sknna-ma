package app.property.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "reviews",
        uniqueConstraints = {
                // Un tenant ne peut laisser qu'un seul avis par annonce
                @UniqueConstraint(
                        name = "uq_review_tenant_property",
                        columnNames = {"property_id", "tenant_id"}
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false, updatable = false)
    @ToString.Exclude
    private Property property;

    // UUID brut — référence Tenant du Groupe B
    @Column(nullable = false, updatable = false)
    private UUID tenantId;

    @Column(nullable = false)
    private Integer rating; // 1 à 5

    @Column(columnDefinition = "TEXT")
    private String comment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}