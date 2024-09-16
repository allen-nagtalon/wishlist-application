package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.domain.Response;
import io.aanagtalon.backend.service.WishService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.aanagtalon.backend.constant.Constants.PHOTO_DIRECTORY;
import static io.aanagtalon.backend.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageResource {
    private final WishService wishService;

    @GetMapping(path = "/{type}/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("type") String type, @PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + "/" + type + "/" + filename));
    }

    @PutMapping(value = "/wish/upload", consumes = "multipart/form-data")
    public ResponseEntity<Response> uploadWishPhoto(@RequestParam("wishId") String wishId, @RequestParam("image") MultipartFile image, HttpServletRequest request) {
        wishService.uploadPhoto(wishId, image);
        return ResponseEntity
                .ok()
                .body(getResponse(request, emptyMap(), "Image has been uploaded for wish", OK));
    }

    @PutMapping("/wishlist/upload")
    public ResponseEntity<Response> uploadWishlistPhoto(@RequestParam("image") MultipartFile image, HttpServletRequest request) {
        return ResponseEntity
                .ok()
                .body(getResponse(request, emptyMap(), "Image has been uploaded for wishlist", OK));
    }

    @PutMapping("/user/upload")
    public ResponseEntity<Response> uploadUserPhoto(@RequestParam("image") MultipartFile image, HttpServletRequest request) {
        return ResponseEntity
                .ok()
                .body(getResponse(request, emptyMap(), "Image has been uploaded for user", OK));
    }
}
