package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.domain.Response;
import io.aanagtalon.backend.dto.WishlistRequest;
import io.aanagtalon.backend.service.JwtService;
import io.aanagtalon.backend.service.WishlistService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

import static io.aanagtalon.backend.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistResource {
    private final WishlistService wishlistService;
    private final JwtService jwtService;

    @PostMapping()
    public ResponseEntity<Response> createWishlist(@RequestHeader(name = "Authorization") String token, @RequestBody WishlistRequest wishlist, HttpServletRequest request) {
        var ownerId = jwtService.extractUserId(token.substring(4));
        wishlistService.createWishlist(wishlist.getTitle(), wishlist.getDescription(), ownerId);
        return ResponseEntity
                .created(URI.create("/wishlist/id"))
                .body(getResponse(request, emptyMap(), "Wishlist created.", CREATED));
    }

}
