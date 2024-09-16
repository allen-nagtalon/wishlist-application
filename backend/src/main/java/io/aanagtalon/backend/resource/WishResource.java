package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.domain.Response;
import io.aanagtalon.backend.dto.WishRequest;
import io.aanagtalon.backend.entity.WishEntity;
import io.aanagtalon.backend.entity.exception.ApiException;
import io.aanagtalon.backend.service.WishService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

import static io.aanagtalon.backend.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishResource {
    private final WishService wishService;

    @PostMapping("/create")
    public ResponseEntity<Response> createWish(@RequestBody WishRequest wish, HttpServletRequest request) {
        var result = wishService.createWish(wish.getTitle(), wish.getDescription(), wish.getUrl(), wish.getWishlistId());
        return ResponseEntity
                .created(URI.create("/wish/id"))
                .body(getResponse(request, Map.of("result", result), "Wish created.", CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishEntity> getWish(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(wishService.getWish(id).orElseThrow(() -> new ApiException("Wish of " + id + " not found")));
    }

    @GetMapping(path = "/wishlist/{id}")
    public ResponseEntity<Response> getWishesByWishlistId(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        return ResponseEntity.ok()
                .body(getResponse(request, Map.of("wishes", wishService.getWishesByWishlistId(id)), "Wishes from wishlist id " + id + " have been fetched", OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteWish(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        wishService.deleteWish(id);
        return ResponseEntity.ok()
                .body(getResponse(request, emptyMap(), "Wish of ID " + id + " has been successfully deleted.", OK));
    }
}
