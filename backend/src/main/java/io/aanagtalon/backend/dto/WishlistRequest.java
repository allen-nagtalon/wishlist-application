package io.aanagtalon.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WishlistRequest {
    @NotEmpty(message = "Title of wishlist cannot be null")
    private String title;

    private String description;

    @NotEmpty(message = "Owner ID of the wishlist cannot be null")
    private Long ownerId;
}
