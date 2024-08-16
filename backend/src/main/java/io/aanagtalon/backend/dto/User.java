package io.aanagtalon.backend.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String userId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private boolean accountNonLocked;
    private boolean enabled;
}
