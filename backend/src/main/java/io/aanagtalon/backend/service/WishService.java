package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.WishEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WishService {
    Page<WishEntity> getAllWishes(int page, int size);
    List<WishEntity> getWishesByWishlistId(long id);
    WishEntity getWish(Long id);
    WishEntity createWish(String title, String description, String url, Long wishlistId);
    void deleteWish(WishEntity wishEntity);
    String uploadPhoto(String userId, MultipartFile file);
}
