package app.booking.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "contracts",
        uniqueConstraints = {
                // Un seul contrat par booking
                @UniqueConstraint(
                        name = "uq_contract_booking",
                        columnNames = {"booking_id"}
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    // Relation 1-1 avec Booking — un contrat = un booking accepté
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false, updatable = false)
    @ToString.Exclude
    private Booking booking;

    // UUIDs bruts pour landlord et tenant
    @Column(nullable = false, updatable = false)
    private UUID landlordId;

    @Column(nullable = false, updatable = false)
    private UUID tenantId;

    @Column(nullable = false, updatable = false)
    private UUID propertyId;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate; // null = durée indéterminée

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private ContractStatus status = ContractStatus.active;

    // Confirmation des deux parties dans l'app
    @Column(nullable = false)
    @Builder.Default
    private Boolean landlordConfirmed = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean tenantConfirmed = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}