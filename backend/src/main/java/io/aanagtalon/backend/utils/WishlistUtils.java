package io.aanagtalon.backend.utils;

import io.aanagtalon.backend.entity.UserEntity;
import io.aanagtalon.backend.entity.WishlistEntity;

import java.util.UUID;

public class WishlistUtils {
    public static WishlistEntity createNewEntity(String title, String description, UserEntity owner) {
        return WishlistEntity.builder()
                .wishlistId(UUID.randomUUID().toString())
                .title(title)
                .description(description)
                .owner(owner)
                .imageUrl("https://cdn0.iconfinder.com/data/icons/rounded-basics/24/svg-rounded_list-512.png")
                .build();
    }
}
