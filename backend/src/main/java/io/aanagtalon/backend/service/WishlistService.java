package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.WishlistEntity;

public interface WishlistService {
    public WishlistEntity createWishlist(String title, Long ownerId);
}
