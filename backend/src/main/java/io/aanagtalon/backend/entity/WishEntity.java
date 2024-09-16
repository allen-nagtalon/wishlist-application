package io.aanagtalon.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "wishes")
public class WishEntity extends Auditable {
    @Column(updatable = false, unique = true, nullable = false)
    private String wishId;

    private String title;
    private String description;
    private String url;
    private String imageUrl;

    @ManyToMany(mappedBy = "wishes", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JsonIgnore
    private Set<WishlistEntity> wishlists = new HashSet<>();

    @PreRemove
    private void removeWishlistAssociations() {
        for (WishlistEntity wishlist : this.wishlists) {
            wishlist.getWishes().remove(this);
        }
    }
}
