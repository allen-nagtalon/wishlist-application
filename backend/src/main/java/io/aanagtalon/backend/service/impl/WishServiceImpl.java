package io.aanagtalon.backend.service.impl;

import io.aanagtalon.backend.entity.WishEntity;
import io.aanagtalon.backend.entity.exception.ApiException;
import io.aanagtalon.backend.repo.WishRepo;
import io.aanagtalon.backend.repo.WishlistRepo;
import io.aanagtalon.backend.service.WishService;
import io.aanagtalon.backend.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static io.aanagtalon.backend.utils.ImageUtils.photoFunction;
import static io.aanagtalon.backend.utils.WishUtils.createNewWishEntity;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {
    private final WishlistRepo wishlistRepo;
    private final WishRepo wishRepo;

    public Page<WishEntity> getAllWishes(int page, int size) {
        return wishRepo.findAll(PageRequest.of(page, size, Sort.by("id")));
    }

    @Override
    public List<WishEntity> getWishesByWishlistId(long id) {
        return wishRepo.findByWishlists_Id(id);
    }

    public WishEntity getWish(Long id) {
        return wishRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public WishEntity createWish(String title, String description, String url, Long wishlistId, MultipartFile file) {
        // Create new wish entity with given fields
        var wish = wishRepo.save(createNewWishEntity(title, description, url));

        // Fetch related wishlist by ID
        var wishlist = wishlistRepo.findById(wishlistId).orElseThrow(() -> new ApiException("Wishlist of ID " + wishlistId + "cannot be found"));

        // Add wish and wishlist to each other's respective sets
        wish.addToWishlist(wishlist);
        wishlist.addWishToWishlist(wish);

        // If image file field is not null, upload photo and apply url to wish entity
        if (file != null) {
            wish.setPhotoUrl(photoFunction.apply(wish.getId().toString(), file));
        } // ADD ELSE FOR DEFAULT PHOTO

        // Return saved wish entity
        return wishRepo.save(wish);
    }

    public void deleteWish(WishEntity wishEntity) {
        // ADD LATER
    }

    public String uploadPhoto(Long id, MultipartFile file) {
        log.info("Saving photo for wish ID: {}", id);
        WishEntity wishEntity = getWish(id);
        String photoUrl = photoFunction.apply(id.toString(), file);
        wishEntity.setPhotoUrl(photoUrl);
        wishRepo.save(wishEntity);
        return photoUrl;
    }
}
