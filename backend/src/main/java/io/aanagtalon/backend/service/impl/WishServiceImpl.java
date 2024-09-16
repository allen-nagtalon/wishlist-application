package io.aanagtalon.backend.service.impl;

import io.aanagtalon.backend.entity.WishEntity;
import io.aanagtalon.backend.entity.exception.ApiException;
import io.aanagtalon.backend.repo.WishRepo;
import io.aanagtalon.backend.repo.WishlistRepo;
import io.aanagtalon.backend.service.WishService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static io.aanagtalon.backend.enumeration.ImageType.WISH;
import static io.aanagtalon.backend.utils.ImageUtils.photoFunction;
import static io.aanagtalon.backend.utils.WishUtils.createNewEntity;

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

    public WishEntity createWish(String title, String description, String url, Long wishlistId) {
        // Create new wish entity with given fields
        var wish = wishRepo.save(createNewEntity(title, description, url));

        // Fetch related wishlist by ID
        var wishlist = wishlistRepo.findById(wishlistId).orElseThrow(() -> new ApiException("Wishlist of ID " + wishlistId + "cannot be found"));

        // Add wish and wishlist to each other's respective sets
        wishlist.addWishToWishlist(wish);
        wishlistRepo.save(wishlist);

        // Return saved wish entity
        return wishRepo.save(wish);
    }

    public void deleteWish(WishEntity wishEntity) {
        // ADD LATER
    }

    public String uploadPhoto(String wishId, MultipartFile file) {
        log.info("Saving photo for wish ID: {}", wishId);
        WishEntity wishEntity = getWishByWishId(wishId);
        String photoUrl = photoFunction(wishEntity.getWishId(), file, WISH.toString());
        wishEntity.setImageUrl(photoUrl);
        wishRepo.save(wishEntity);
        return photoUrl;
    }

    private WishEntity getWishByWishId(String wishId) {
        return wishRepo.findByWishId(wishId).orElseThrow(() -> new ApiException("Wish cannot be found by wishId: " + wishId));
    }
}
