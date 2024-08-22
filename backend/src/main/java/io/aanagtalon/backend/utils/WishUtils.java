package io.aanagtalon.backend.utils;

import io.aanagtalon.backend.entity.WishEntity;

public class WishUtils {
    public static WishEntity createNewWishEntity(String title, String description, String url) {
        return new WishEntity(title, description, url);
    }
}
