package io.aanagtalon.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserEntity extends Auditable {
    @Column(updatable = false, unique = true, nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;
    
    private String firstName;
    private String lastName;
    private String imageUrl;
    private boolean accountNonLocked;
    private boolean enabled;
    private int loginAttempts;
  
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<WishlistEntity> wishlists;

    @ManyToMany()
    @JoinTable(name = "user_follows",
        joinColumns = {@JoinColumn(name = "followedId")},
        inverseJoinColumns = {@JoinColumn(name = "followerId")}
    )
    @JsonIgnore
    private Set<UserEntity> followers = new HashSet<UserEntity>();

    @ManyToMany()
    @JoinTable(name = "user_follows",
            joinColumns = {@JoinColumn(name = "followerId")},
            inverseJoinColumns = {@JoinColumn(name = "followedId")}
    )
    @JsonIgnore
    private Set<UserEntity> following = new HashSet<UserEntity>();

    public void addFollower(UserEntity user) {
        this.followers.add(user);
    }
}
