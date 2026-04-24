package app.post.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostRequestDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String title;
    @Size(max = 500)
    private String description;
}
