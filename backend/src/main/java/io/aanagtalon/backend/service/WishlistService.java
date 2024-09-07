package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.WishlistEntity;

import java.util.List;

public interface WishlistService {
    WishlistEntity createWishlist(String title, String description, Long ownerId);
    List<WishlistEntity> getWishlistsByOwnerId(long ownerId);
}
