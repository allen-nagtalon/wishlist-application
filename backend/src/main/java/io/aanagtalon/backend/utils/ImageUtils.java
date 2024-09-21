package io.aanagtalon.backend.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;

import static io.aanagtalon.backend.constant.Constants.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ImageUtils {

    private static final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    public static String photoFunction(String id, MultipartFile image, String type) {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY + "/" + type + "/").toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/image/" + type + "/" + filename).toUriString();
        } catch (Exception exception) {
            throw new RuntimeException("Unable to save image!");
        }
    }

    public static void deleteImage(String imageUrl) {
        String[] splitUrl = imageUrl.split("/");
        var type = splitUrl[4];
        var filename = splitUrl[5];

        try {
            Path fileLocation = Paths.get(PHOTO_DIRECTORY + "/" + type + "/" + filename).toAbsolutePath().normalize();
            Files.deleteIfExists(fileLocation);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete image!");
        }
    }
}
