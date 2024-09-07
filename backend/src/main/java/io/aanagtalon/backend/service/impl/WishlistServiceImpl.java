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

import java.util.List;

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
    public List<WishlistEntity> getWishlistsByOwnerId(long ownerId) {
        return wishlistRepo.findByOwner_Id(ownerId);
    }
}
