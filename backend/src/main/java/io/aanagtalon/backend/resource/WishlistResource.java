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

    @GetMapping("/{id}")
    public ResponseEntity<Response> getWishlistInfo(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        return ResponseEntity.ok().body(getResponse(request, Map.of("wishlist", wishlistService.getWishlistById(id)), "Wishlist ID " + id + " retrieved", OK));
    }

    @PostMapping()
    public ResponseEntity<Response> createWishlist(@RequestHeader(name = "Authorization") String token, @RequestBody WishlistRequest wishlist, HttpServletRequest request) {
        var ownerId = jwtService.extractUserId(token.substring(4));
        var result = wishlistService.createWishlist(wishlist.getTitle(), wishlist.getDescription(), ownerId);
        return ResponseEntity
                .created(URI.create("/wishlist/id"))
                .body(getResponse(request, Map.of("result", result), "Wishlist created.", CREATED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteWishlist(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.ok()
                .body(getResponse(request, emptyMap(), "Wishlist id " + id + " has been deleted", OK));
    }
}
