package io.aanagtalon.backend.service;

import io.aanagtalon.backend.entity.CredentialEntity;
import io.aanagtalon.backend.entity.UserEntity;

import java.util.List;

public interface UserService {
    void createUser(String username, String firstName, String lastName, String email, String password);
    void verifyAccountKey(String key);
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    UserEntity getUserByUsername(String username);
    UserEntity getUserByEmail(String email);
    CredentialEntity getUserCredentialById(Long id);
    void followUserById(Long followerId, Long recipientId);
}
