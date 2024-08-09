package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.entity.WishEntity;
import io.aanagtalon.backend.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.aanagtalon.backend.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishResource {
    private final WishService wishService;

    @PostMapping
    public ResponseEntity<WishEntity> createWish(@RequestBody WishEntity wishEntity) {
        return ResponseEntity.created(URI.create("/wishes/id")).body(wishService.createWish(wishEntity));
    }

    @GetMapping
    public ResponseEntity<Page<WishEntity>> getContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(wishService.getAllWishes(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishEntity> getWish(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(wishService.getWish(id));
    }

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(wishService.uploadPhoto(id, file));
    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
