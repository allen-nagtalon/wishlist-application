package io.aanagtalon.backend.service.impl;

import io.aanagtalon.backend.entity.WishlistEntity;
import io.aanagtalon.backend.entity.exception.ApiException;
import io.aanagtalon.backend.repo.UserRepo;
import io.aanagtalon.backend.repo.WishRepo;
import io.aanagtalon.backend.repo.WishlistRepo;
import io.aanagtalon.backend.service.WishlistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static io.aanagtalon.backend.enumeration.ImageType.WISHLIST;
import static io.aanagtalon.backend.utils.ImageUtils.photoFunction;
import static io.aanagtalon.backend.utils.WishlistUtils.createNewEntity;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class WishlistServiceImpl implements WishlistService {
    private final UserRepo userRepo;
    private final WishRepo wishRepo;
    private final WishlistRepo wishlistRepo;

    @Override
    public WishlistEntity createWishlist(String title, String description, Long ownerId) {
        var owner = userRepo.findById(ownerId).orElseThrow(() -> new ApiException("User not found"));
        return wishlistRepo.save(createNewEntity(title, description, owner));
    }

    public WishlistEntity getWishlistById(Long id) {
        return wishlistRepo.findById(id).orElseThrow(() -> new ApiException("Wishlist id " + id + " not found"));
    }

    @Override
    public List<WishlistEntity> getWishlistsByOwnerId(long ownerId) {
        return wishlistRepo.findByOwner_Id(ownerId);
    }

    @Override
    public String uploadPhoto(Long id, MultipartFile file) {
        log.info("Saving photo for wishlist ID: {}", id);
        var wishlistEntity = getWishlistById(id);
        String imageUrl = photoFunction(wishlistEntity.getWishlistId(), file, WISHLIST.name());
        wishlistEntity.setImageUrl(imageUrl);
        wishlistRepo.save(wishlistEntity);
        return imageUrl;
    }

    @Override
    public void deleteWishlist(Long id) {
        // Fetch wishlist by the given id
        var wishlist = getWishlistById(id);

        // Delete the wishlist
        wishlistRepo.delete(wishlist);
    }
}
