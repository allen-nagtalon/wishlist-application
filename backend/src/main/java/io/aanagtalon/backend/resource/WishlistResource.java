package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.domain.Response;
import io.aanagtalon.backend.dto.WishlistRequest;
import io.aanagtalon.backend.service.JwtService;
import io.aanagtalon.backend.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

import static io.aanagtalon.backend.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistResource {
    private final WishlistService wishlistService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<Response> getUserWishlists(@RequestHeader(name = "Authorization") String token, HttpServletRequest request) {
        var ownerId = jwtService.extractUserId(token.substring(4));
        return ResponseEntity.ok().body(getResponse(request, Map.of("wishlists", wishlistService.getWishlistsByOwnerId(ownerId)), "Wishlists of user ID " + ownerId + " fetched.", OK));
    }

    @PostMapping()
    public ResponseEntity<Response> createWishlist(@RequestHeader(name = "Authorization") String token, @RequestBody WishlistRequest wishlist, HttpServletRequest request) {
        var ownerId = jwtService.extractUserId(token.substring(4));
        wishlistService.createWishlist(wishlist.getTitle(), wishlist.getDescription(), ownerId);
        return ResponseEntity
                .created(URI.create("/wishlist/id"))
                .body(getResponse(request, emptyMap(), "Wishlist created.", CREATED));
    }
}
