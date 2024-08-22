package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.WishEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface WishService {
    Page<WishEntity> getAllWishes(int page, int size);
    WishEntity getWish(Long id);
    WishEntity createWish(String title, String description, String url, Long wishlistId, MultipartFile file);
    void deleteWish(WishEntity wishEntity);
    String uploadPhoto(Long id, MultipartFile file);
}
