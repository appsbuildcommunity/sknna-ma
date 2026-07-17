package app.property.model;

import app.booking.model.Booking;
import app.post.model.PostLandlord;
import app.post.model.PostTenant;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    // UUID brut — pas de FK JPA vers User
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

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "property_tags",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private PostLandlord landlordPost;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTenant> tenantPosts = new ArrayList<>();
}