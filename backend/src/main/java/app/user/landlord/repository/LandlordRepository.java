package app.user.landlord.repository;

import app.user.landlord.model.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, UUID> {

    Optional<Landlord> findByEmail(String email);

    boolean existsByEmail(String email);
}