package io.aanagtalon.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String description;
    private String photoUrl;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity owner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(
            name = "wishlist_items",
            joinColumns = @JoinColumn(name = "wishlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wish_id", referencedColumnName = "id")
    )
    @JsonIgnore
    private Set<WishEntity> wishes;

    public WishlistEntity(String title, String description, UserEntity owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public void addWishToWishlist(WishEntity wish) {
        wishes.add(wish);
    }
}
