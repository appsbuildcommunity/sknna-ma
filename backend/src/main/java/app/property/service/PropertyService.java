package app.property.service;

import app.common.exception.ForbiddenException;
import app.common.exception.ResourceNotFoundException;
import app.property.dto.PropertyMapper;
import app.property.dto.PropertyRequest;
import app.property.dto.PropertyResponse;
import app.property.model.Property;
import app.property.model.Tag;
import app.property.repository.PropertyRepository;
import app.property.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final TagRepository tagRepository;
    private final PropertyMapper propertyMapper;

    // ── GET liste paginée publique ────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<PropertyResponse> getAvailableProperties(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());
        Page<Property> result = propertyRepository.findByIsAvailableTrue(pageable);
        return propertyMapper.toResponseList(result.getContent());
    }

    // ── GET liste du landlord connecté ────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<PropertyResponse> getMyProperties(UUID landlordId, int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());
        Page<Property> result = propertyRepository.findByLandlordId(landlordId, pageable);
        return propertyMapper.toResponseList(result.getContent());
    }

    // ── GET détail d'une annonce ──────────────────────────────────────────────
    @Transactional(readOnly = true)
    public PropertyResponse getPropertyById(UUID id) {
        Property property = findPropertyOrThrow(id);
        return propertyMapper.toResponse(property);
    }

    // ── POST créer une annonce ────────────────────────────────────────────────
    @Transactional
    public PropertyResponse createProperty(PropertyRequest request, UUID landlordId) {
        Property property = propertyMapper.toEntity(request);
        property.setLandlordId(landlordId);
        property.setIsAvailable(true);
        property.setTags(resolveTags(request.getTags()));
        Property saved = propertyRepository.save(property);
        return propertyMapper.toResponse(saved);
    }

    // ── PUT modifier une annonce ──────────────────────────────────────────────
    @Transactional
    public PropertyResponse updateProperty(UUID id, PropertyRequest request, UUID landlordId) {
        Property property = findPropertyOrThrow(id);
        checkOwnership(property, landlordId);
        propertyMapper.updateEntity(request, property);
        property.setTags(resolveTags(request.getTags()));
        return propertyMapper.toResponse(propertyRepository.save(property));
    }

    // ── DELETE supprimer une annonce (soft delete) ────────────────────────────
    @Transactional
    public void deleteProperty(UUID id, UUID landlordId) {
        Property property = findPropertyOrThrow(id);
        checkOwnership(property, landlordId);
        property.setIsAvailable(false);
        propertyRepository.save(property);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private Property findPropertyOrThrow(UUID id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annonce introuvable"));
    }

    private void checkOwnership(Property property, UUID landlordId) {
        if (!property.getLandlordId().equals(landlordId)) {
            throw new ForbiddenException("Vous n'êtes pas autorisé à modifier cette annonce");
        }
    }

    // Réutilise un tag existant ou en crée un nouveau — logique ManyToMany
    private Set<Tag> resolveTags(List<String> tagNames) {
        Set<Tag> tags = new HashSet<>();
        if (tagNames == null) return tags;
        for (String name : tagNames) {
            String normalized = name.trim().toLowerCase();
            Tag tag = tagRepository.findByName(normalized)
                    .orElseGet(() -> tagRepository.save(
                            Tag.builder().name(normalized).build()
                    ));
            tags.add(tag);
        }
        return tags;
    }
}