package io.aanagtalon.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WishRequest {
    @NotEmpty(message = "Title cannot be empty or null")
    private String title;

    private String description;

    private String url;

    @NotEmpty(message = "Wish must be assigned to a wishlist")
    private Long wishlistId;

    private MultipartFile file;
}
