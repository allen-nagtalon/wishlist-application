package io.aanagtalon.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "wishes")
public class WishEntity extends Auditable {

    private String title;
    private String description;
    private String url;
    private String photoUrl;

    @ManyToMany(mappedBy = "wishes")
    @JsonIgnore
    private Set<WishlistEntity> wishlists = new HashSet<>();

    public WishEntity(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public void addToWishlist(WishlistEntity wishlist) {
        wishlists.add(wishlist);
    }
}
