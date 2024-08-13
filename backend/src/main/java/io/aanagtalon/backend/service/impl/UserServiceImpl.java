package io.aanagtalon.backend.service.impl;

import io.aanagtalon.backend.cache.CacheStore;
import io.aanagtalon.backend.entity.ConfirmationEntity;
import io.aanagtalon.backend.entity.CredentialEntity;
import io.aanagtalon.backend.entity.UserEntity;
import io.aanagtalon.backend.entity.exception.WishlistException;
import io.aanagtalon.backend.enumeration.EventType;
import io.aanagtalon.backend.enumeration.LoginType;
import io.aanagtalon.backend.event.UserEvent;
import io.aanagtalon.backend.repo.ConfirmationRepo;
import io.aanagtalon.backend.repo.CredentialRepo;
import io.aanagtalon.backend.repo.UserRepo;
import io.aanagtalon.backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static io.aanagtalon.backend.utils.UserUtils.createNewEntity;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final CredentialRepo credentialRepo;
    private final ConfirmationRepo confirmationRepo;
    //private final BCryptPasswordEncoder encoder;
    private final CacheStore<String, Integer> userCache;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepo.save(createNewEntity(firstName, lastName, email));
        var credentialEntity = new CredentialEntity(userEntity, password);
        credentialRepo.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepo.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));
    }

    @Override
    public void verifyAccountKey(String key) {
        var confirmationEntity = (ConfirmationEntity) getUserConfirmation(key);
        var userEntity = getUserEntityByEmail(confirmationEntity.getUserEntity().getEmail());
        userEntity.setEnabled(true);
        userRepo.save(userEntity);
        confirmationRepo.delete(confirmationEntity);
    }

    @Override
    public void updateLoginAttempt(String email, LoginType loginType) {
        var userEntity = getUserEntityByEmail(email);
        switch(loginType) {
            case LOGIN_ATTEMPT -> {
                if(userCache.get(userEntity.getEmail()) == null) {
                    userEntity.setLoginAttempts(0);
                    userEntity.setAccountNonLocked(true);
                }
                userEntity.setLoginAttempts(userEntity.getLoginAttempts() + 1);
                userCache.put(userEntity.getEmail(), userEntity.getLoginAttempts());
                if (userCache.get(userEntity.getEmail()) > 5) {
                    userEntity.setAccountNonLocked(false);
                }
            }
            case LOGIN_SUCCESS -> {
                userEntity.setAccountNonLocked(true);
                userEntity.setLoginAttempts(0);
                userCache.evict(userEntity.getEmail());
            }
        }
        userRepo.save(userEntity);
    }

    private UserEntity getUserEntityByEmail(String email) {
        var userByEmail = userRepo.findByEmailIgnoreCase(email);
        return userByEmail.orElseThrow(() -> new WishlistException("User not found"));
    }

    private ConfirmationEntity getUserConfirmation(String key) {
        return confirmationRepo.findByKey(key).orElseThrow(() -> new WishlistException("Confirmation key not found"));
    }
}
