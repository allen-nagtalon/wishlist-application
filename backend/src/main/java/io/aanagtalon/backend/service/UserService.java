package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.CredentialEntity;
import io.aanagtalon.backend.entity.UserEntity;

public interface UserService {
    void createUser(String username, String firstName, String lastName, String email, String password);
    void verifyAccountKey(String key);
    UserEntity getUserByUsername(String username);
    UserEntity getUserByEmail(String email);
    CredentialEntity getUserCredentialById(Long id);
}
