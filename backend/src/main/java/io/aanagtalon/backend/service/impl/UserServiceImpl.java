package io.aanagtalon.backend.service.impl;

import io.aanagtalon.backend.cache.CacheStore;
import io.aanagtalon.backend.entity.ConfirmationEntity;
import io.aanagtalon.backend.entity.CredentialEntity;
import io.aanagtalon.backend.entity.UserEntity;
import io.aanagtalon.backend.entity.exception.ApiException;
import io.aanagtalon.backend.enumeration.EventType;
import io.aanagtalon.backend.event.UserEvent;
import io.aanagtalon.backend.repo.ConfirmationRepo;
import io.aanagtalon.backend.repo.CredentialRepo;
import io.aanagtalon.backend.repo.UserRepo;
import io.aanagtalon.backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder encoder;
    private final CacheStore<String, Integer> userCache;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String username, String firstName, String lastName, String email, String password) {
        var userEntity = userRepo.save(createNewEntity(username, firstName, lastName, email));
        var credentialEntity = new CredentialEntity(userEntity, encoder.encode(password));
        credentialRepo.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepo.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));
    }

    @Override
    public void verifyAccountKey(String key) {
        var confirmationEntity = (ConfirmationEntity) getUserConfirmation(key);
        var userEntity = getUserByEmail(confirmationEntity.getUserEntity().getEmail());
        userEntity.setEnabled(true);
        userRepo.save(userEntity);
        confirmationRepo.delete(confirmationEntity);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new ApiException("Username " + username + " could not be found"));
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepo.findByEmailIgnoreCase(email).orElseThrow(() -> new ApiException("Email " + email + " could not be found"));
    }

    @Override
    public CredentialEntity getUserCredentialById(Long userId) {
        var credentialById = credentialRepo.getCredentialByUserEntityId(userId);
        return credentialById.orElseThrow(() -> new ApiException("Unable to find user credential"));
    }

    private ConfirmationEntity getUserConfirmation(String key) {
        return confirmationRepo.findByKey(key).orElseThrow(() -> new ApiException("Confirmation key not found"));
    }
}
