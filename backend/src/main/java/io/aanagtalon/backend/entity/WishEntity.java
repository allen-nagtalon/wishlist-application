package io.aanagtalon.backend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

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
    private Set<WishlistEntity> wishlists;
}
