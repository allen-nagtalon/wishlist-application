package io.aanagtalon.backend.service;

import io.aanagtalon.backend.dto.User;
import io.aanagtalon.backend.entity.CredentialEntity;
import io.aanagtalon.backend.enumeration.LoginType;

public interface UserService {
    void createUser(String firstName, String lastName,String email, String password);
    void verifyAccountKey(String key);
    void updateLoginAttempt(String email, LoginType loginType);
    User getUserByUserId(String userId);
    User getUserByEmail(String email);
    CredentialEntity getUserCredentialById(Long id);
}
