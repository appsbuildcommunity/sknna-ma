package app.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PostResponseDto {
    UUID id;
    String title;
    String description;
    Boolean isActive;
    LocalDateTime createdAt;
    List<PictureDto> pictures;
}
