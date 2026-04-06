package app.property.dto;

import app.property.model.PropertyType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PropertyRequest {

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 150, message = "Le titre ne peut pas dépasser 150 caractères")
    private String title;

    private String description;

    @NotNull(message = "Le type est obligatoire")
    private PropertyType type;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
    private BigDecimal price;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String neighborhood;

    private Double latitude;
    private Double longitude;

    @NotNull(message = "La date de disponibilité est obligatoire")
    @FutureOrPresent(message = "La date de disponibilité ne peut pas être dans le passé")
    private LocalDate availableFrom;

    private List<String> tags = new ArrayList<>();
}