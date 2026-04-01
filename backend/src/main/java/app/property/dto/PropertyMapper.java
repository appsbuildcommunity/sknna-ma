package app.property.dto;

import app.property.model.Property;
import app.property.model.Tag;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "landlordId", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "tenantPosts", ignore = true)
    @Mapping(target = "landlordPost", ignore = true)
    @Mapping(target = "tags", ignore = true)
    Property toEntity(PropertyRequest request);

    @Mapping(target = "tags", expression = "java(mapTags(property.getTags()))")
    @Mapping(target = "pictures", expression = "java(mapPictures(property))")
    PropertyResponse toResponse(Property property);

    List<PropertyResponse> toResponseList(List<Property> properties);

    default List<String> mapTags(Set<Tag> tags) {
        if (tags == null) return List.of();
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }

    default List<String> mapPictures(Property property) {
        return List.of();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "landlordId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "tenantPosts", ignore = true)
    @Mapping(target = "landlordPost", ignore = true)
    @Mapping(target = "tags", ignore = true)
    void updateEntity(PropertyRequest request, @MappingTarget Property property);
}