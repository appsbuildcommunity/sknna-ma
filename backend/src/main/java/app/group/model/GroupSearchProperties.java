package app.group.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "groupe_search_properties")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupSearchProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(precision = 10, scale = 2)
    private BigDecimal budgetMin;

    @Column(precision = 10, scale = 2)
    private BigDecimal budgetMax;

    @Column(length = 20)
    private String propertyType;

    // UUID brut vers Tenant (créateur du groupe)
    @Column(nullable = false, updatable = false)
    private UUID creatorId;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<GroupMember> members = new ArrayList<>();

    // après la liste members
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<app.post.model.PostTenant> posts = new ArrayList<>();

    // UUIDs des annonces sauvegardées — simple, pas d'entité jointure
    @ElementCollection
    @CollectionTable(
            name = "group_saved_properties",
            joinColumns = @JoinColumn(name = "group_id")
    )
    @Column(name = "property_id")
    @Builder.Default
    private List<UUID> savedPropertyIds = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}