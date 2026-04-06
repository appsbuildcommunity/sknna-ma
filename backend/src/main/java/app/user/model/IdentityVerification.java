package app.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "identity_verifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    // UUID brut vers User — un seul document par utilisateur
    @Column(nullable = false, unique = true)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private VerificationStatus status = VerificationStatus.pending;

    @Column(length = 300)
    private String documentUrl; // URL du document uploadé

    @Column(length = 300)
    private String rejectionReason; // Rempli par admin si refusé

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime submittedAt;

    @UpdateTimestamp
    private LocalDateTime reviewedAt;
}