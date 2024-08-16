package io.aanagtalon.backend.utils;

import io.aanagtalon.backend.dto.User;
import io.aanagtalon.backend.entity.CredentialEntity;
import io.aanagtalon.backend.entity.UserEntity;
import org.springframework.beans.BeanUtils;

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

    public static User fromUserEntity(UserEntity userEntity, CredentialEntity credentialEntity) {
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }
}
