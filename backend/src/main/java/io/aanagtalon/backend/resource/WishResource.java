package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.domain.Response;
import io.aanagtalon.backend.dto.WishRequest;
import io.aanagtalon.backend.entity.WishEntity;
import io.aanagtalon.backend.service.WishService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static io.aanagtalon.backend.constant.Constants.PHOTO_DIRECTORY;
import static io.aanagtalon.backend.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

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

    @GetMapping
    public ResponseEntity<Page<WishEntity>> getContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(wishService.getAllWishes(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishEntity> getWish(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(wishService.getWish(id));
    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + "/wishes/" + filename));
    }

    @GetMapping(path = "/wishlist/{id}")
    public ResponseEntity<Response> getWishesByWishlistId(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        return ResponseEntity.ok()
                .body(getResponse(request, Map.of("wishes", wishService.getWishesByWishlistId(id)), "Wishes from wishlist id " + id + " have been fetched", OK));
    }
}
