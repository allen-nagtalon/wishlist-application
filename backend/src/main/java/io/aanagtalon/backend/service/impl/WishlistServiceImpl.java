package io.aanagtalon.backend.service.impl;

import io.aanagtalon.backend.entity.WishlistEntity;
import io.aanagtalon.backend.entity.exception.ApiException;
import io.aanagtalon.backend.repo.UserRepo;
import io.aanagtalon.backend.repo.WishlistRepo;
import io.aanagtalon.backend.service.WishlistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static io.aanagtalon.backend.utils.ImageUtils.photoFunction;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class WishlistServiceImpl implements WishlistService {
    private final UserRepo userRepo;
    private final WishlistRepo wishlistRepo;

    @Override
    public WishlistEntity createWishlist(String title, String description, Long ownerId) {
        var owner = userRepo.findById(ownerId).orElseThrow(() -> new ApiException("User not found"));
        return wishlistRepo.save(new WishlistEntity(title, description, owner));
    }

    @Override
    public Optional<WishlistEntity> getWishlistById(Long id) {
        return wishlistRepo.findById(id);
    }

    @Override
    public List<WishlistEntity> getWishlistsByOwnerId(long ownerId) {
        return wishlistRepo.findByOwner_Id(ownerId);
    }

    @Override
    public String uploadPhoto(Long id, MultipartFile file) {
        log.info("Saving photo for wishlist ID: {}", id);
        var wishlistEntity = getWishlistById(id).orElseThrow(() -> new ApiException("Wishlist could not be found."));
        String photoUrl = photoFunction.apply(id.toString(), file);
        wishlistEntity.setPhotoUrl(photoUrl);
        wishlistRepo.save(wishlistEntity);
        return photoUrl;
    }

}
