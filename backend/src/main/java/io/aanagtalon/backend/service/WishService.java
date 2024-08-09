package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.WishEntity;
import io.aanagtalon.backend.repo.WishRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static io.aanagtalon.backend.constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class WishService {
    private final WishRepo wishRepo;

    public Page<WishEntity> getAllWishes(int page, int size) {
        return wishRepo.findAll(PageRequest.of(page, size, Sort.by("id")));
    }

    public WishEntity getWish(String id) {
        return wishRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public WishEntity createWish(WishEntity wishEntity) {
        return wishRepo.save(wishEntity);
    }

    public void deleteWish(WishEntity wishEntity) {
        // ADD LATER
    }

    public String uploadPhoto(String id, MultipartFile file) {
        log.info("Saving photo for wish ID: {}", id);
        WishEntity wishEntity = getWish(id);
        String photoUrl = photoFunction.apply(id, file);
        wishEntity.setPhotoUrl(photoUrl);
        wishRepo.save(wishEntity);
        return photoUrl;
    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/wishes/image/" + filename).toUriString();
        } catch (Exception exception) {
            throw new RuntimeException("Unable to save image!");
        }
    };
}
