package app.property.model;

import app.booking.model.Booking;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "properties")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PropertyType type;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 100)
    private String neighborhood;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false)
    private LocalDate availableFrom;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isAvailable = true;

    @ElementCollection
    @CollectionTable(
            name = "property_pictures",
            joinColumns = @JoinColumn(name = "property_id")
    )
    @Column(name = "picture_url", length = 500)
    @Builder.Default
    private List<String> pictures = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "property_tags",
            joinColumns = @JoinColumn(name = "property_id")
    )
    @Column(name = "tag", length = 50)
    @Builder.Default
    private List<String> tags = new ArrayList<>();

    // UUID brut — pas de FK JPA vers User (Groupe B)
    @Column(nullable = false, updatable = false)
    private UUID landlordId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Booking> bookings = new ArrayList<>();
}