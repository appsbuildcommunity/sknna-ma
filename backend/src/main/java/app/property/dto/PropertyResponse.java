package app.property.dto;

import app.property.model.PropertyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class PropertyResponse {

    private UUID id;
    private String title;
    private String description;
    private PropertyType type;
    private BigDecimal price;
    private String city;
    private String neighborhood;
    private Double latitude;
    private Double longitude;
    private LocalDate availableFrom;
    private Boolean isAvailable;
    private List<String> pictures;
    private List<String> tags;
    private UUID landlordId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private LocalDateTime updatedAt;
}