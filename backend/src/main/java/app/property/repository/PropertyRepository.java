package app.property.repository;

import app.property.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {

    Page<Property> findByIsAvailableTrue(Pageable pageable);

    Page<Property> findByLandlordId(UUID landlordId, Pageable pageable);

    Optional<Property> findByIdAndLandlordId(UUID id, UUID landlordId);
}