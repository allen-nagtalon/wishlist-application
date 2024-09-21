package io.aanagtalon.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.aanagtalon.backend.utils.ImageUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Lob
    private String url;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_items", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private WishlistEntity wishlist;

    @PreRemove
    private void deleteImage() {
        ImageUtils.deleteImage(getImageUrl());
    }
}
