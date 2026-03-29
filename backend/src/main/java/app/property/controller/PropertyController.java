package app.property.controller;

import app.property.dto.PropertyRequest;
import app.property.dto.PropertyResponse;
import app.property.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    // GET /api/properties — public
    @GetMapping
    public ResponseEntity<Map<String, Object>> getProperties(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        List<PropertyResponse> data = propertyService.getAvailableProperties(page, limit);
        return ResponseEntity.ok(Map.of("data", data, "page", page, "limit", limit));
    }

    // GET /api/properties/my — landlord connecté
    @GetMapping("/my")
    public ResponseEntity<Map<String, Object>> getMyProperties(
            @RequestHeader("X-User-Id") UUID landlordId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        List<PropertyResponse> data = propertyService.getMyProperties(landlordId, page, limit);
        return ResponseEntity.ok(Map.of("data", data, "page", page, "limit", limit));
    }

    // GET /api/properties/:id — public
    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable UUID id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    // POST /api/properties — landlord seulement
    @PostMapping
    public ResponseEntity<PropertyResponse> createProperty(
            @Valid @RequestBody PropertyRequest request,
            @RequestHeader("X-User-Id") UUID landlordId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(propertyService.createProperty(request, landlordId));
    }

    // PUT /api/properties/:id — landlord seulement
    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> updateProperty(
            @PathVariable UUID id,
            @Valid @RequestBody PropertyRequest request,
            @RequestHeader("X-User-Id") UUID landlordId) {
        return ResponseEntity.ok(propertyService.updateProperty(id, request, landlordId));
    }

    // DELETE /api/properties/:id — landlord seulement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(
            @PathVariable UUID id,
            @RequestHeader("X-User-Id") UUID landlordId) {
        propertyService.deleteProperty(id, landlordId);
        return ResponseEntity.noContent().build();
    }
}