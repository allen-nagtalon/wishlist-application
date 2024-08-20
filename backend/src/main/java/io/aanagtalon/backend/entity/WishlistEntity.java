package io.aanagtalon.backend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wishlists")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class WishlistEntity extends Auditable {
    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinTable(
            name = "user_wishlists",
            joinColumns = @JoinColumn(
                    name = "wishlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private UserEntity owner;

    @ManyToMany
    @JoinTable(
            name = "wishlist_items",
            joinColumns = @JoinColumn(name = "wishlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wish_id", referencedColumnName = "id")
    )
    private Set<WishEntity> wishes;
}
