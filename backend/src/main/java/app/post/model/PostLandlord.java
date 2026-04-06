package app.post.model;

import app.property.model.Property;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Entity
@Table(name = "post_landlords")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostLandlord extends Post {

    @Column(nullable = false, updatable = false)
    private UUID landlordId;

    @Column(precision = 10, scale = 2)
    private BigDecimal budget;

    private LocalDate moveInDate;

    @Column(columnDefinition = "TEXT")
    private String targetProfile;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false, unique = true)
    @ToString.Exclude
    private Property property;
}