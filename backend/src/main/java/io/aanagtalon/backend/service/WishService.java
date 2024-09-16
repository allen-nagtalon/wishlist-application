package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.WishEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface WishService {
    Page<WishEntity> getAllWishes(int page, int size);
    List<WishEntity> getWishesByWishlistId(long id);
    Optional<WishEntity> getWish(Long id);
    WishEntity createWish(String title, String description, String url, Long wishlistId);
    void deleteWish(Long id);
    String uploadPhoto(String userId, MultipartFile file);
}
