package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.WishlistEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WishlistService {
    WishlistEntity createWishlist(String title, String description, Long ownerId);
    WishlistEntity getWishlistById(Long id);
    List<WishlistEntity> getWishlistsByOwnerId(long ownerId);
    String uploadPhoto(Long id, MultipartFile file);
    void deleteWishlist(Long id);
}
