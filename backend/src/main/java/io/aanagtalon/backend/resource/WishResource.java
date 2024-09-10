package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.domain.Response;
import io.aanagtalon.backend.dto.WishRequest;
import io.aanagtalon.backend.entity.WishEntity;
import io.aanagtalon.backend.service.WishService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static io.aanagtalon.backend.constant.Constants.PHOTO_DIRECTORY;
import static io.aanagtalon.backend.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishResource {
    private final WishService wishService;

    @PostMapping
    public ResponseEntity<WishEntity> createWish(@ModelAttribute WishRequest request) {
        var wish = wishService.createWish(request.getTitle(), request.getDescription(), request.getUrl(), request.getWishlistId(), request.getFile());
        return ResponseEntity.created(URI.create("/wishes/id")).body(wish);
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

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(wishService.uploadPhoto(id, file));
    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

    @GetMapping(path = "/wishlist/{id}")
    public ResponseEntity<Response> getWishesByWishlistId(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        return ResponseEntity.ok()
                .body(getResponse(request, Map.of("wishes", wishService.getWishesByWishlistId(id)), "Wishes from wishlist id " + id + " have been fetched", OK));
    }
}
