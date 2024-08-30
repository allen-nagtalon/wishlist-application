package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.dto.WishlistRequest;
import io.aanagtalon.backend.entity.WishlistEntity;
import io.aanagtalon.backend.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistResource {
    private final WishlistService wishlistService;

    @PostMapping("/create")
    public ResponseEntity<WishlistEntity> createWishlist(@RequestBody WishlistRequest request) {
        return ResponseEntity
                .created(URI.create("/wishlist/id"))
                .body(wishlistService.createWishlist(request.getTitle(), request.getDescription(), request.getOwnerId()));
    }

}
