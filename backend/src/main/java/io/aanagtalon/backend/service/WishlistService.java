package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.WishlistEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    WishlistEntity createWishlist(String title, String description, Long ownerId);
    List<WishlistEntity> getWishlistsByOwnerId(long ownerId);
    String uploadPhoto(Long id, MultipartFile file);
    void deleteWishlist(Long id);
}
