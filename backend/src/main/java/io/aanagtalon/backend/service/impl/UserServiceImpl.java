package io.aanagtalon.backend.service.impl;

import io.aanagtalon.backend.entity.ConfirmationEntity;
import io.aanagtalon.backend.entity.CredentialEntity;
import io.aanagtalon.backend.entity.UserEntity;
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
}
