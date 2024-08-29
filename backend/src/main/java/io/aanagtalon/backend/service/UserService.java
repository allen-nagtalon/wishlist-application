package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.CredentialEntity;

public interface UserService {
    void createUser(String username, String firstName, String lastName,String email, String password);
    void verifyAccountKey(String key);
    CredentialEntity getUserCredentialById(Long id);
}
