package io.aanagtalon.backend.utils;

import io.aanagtalon.backend.entity.UserEntity;

import java.util.UUID;

public class UserUtils {
    public static UserEntity createNewEntity(String firstName, String lastName, String email) {
        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .accountNonLocked(true)
                .enabled(false)
                .imageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/510px-Default_pfp.svg.png")
                .build();
    }
}
