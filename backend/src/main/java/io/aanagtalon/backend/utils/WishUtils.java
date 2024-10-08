package io.aanagtalon.backend.utils;

import io.aanagtalon.backend.entity.WishEntity;
import io.aanagtalon.backend.entity.WishlistEntity;

import java.util.UUID;

public class WishUtils {
    public static WishEntity createNewEntity(String title, String description, String url, WishlistEntity wishlist) {
        return WishEntity.builder()
                .wishId(UUID.randomUUID().toString())
                .title(title)
                .description(description)
                .url(url)
                .wishlist(wishlist)
                .imageUrl("https://thumb.silhouette-ac.com/t/c6/c605e9088ce5521e6c0c577951c213eb_t.jpeg")
                .build();
    }
}
