package io.aanagtalon.backend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserEntity {
    @Column(updatable = false, unique = true, nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private boolean accountNonLocked;
    private boolean enabled;
}
