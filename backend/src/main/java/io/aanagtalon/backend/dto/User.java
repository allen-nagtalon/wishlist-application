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
    private String authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
