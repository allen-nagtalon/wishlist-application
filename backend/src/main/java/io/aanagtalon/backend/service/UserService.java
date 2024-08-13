package io.aanagtalon.backend.service;

import io.aanagtalon.backend.enumeration.LoginType;

public interface UserService {
    void createUser(String firstName, String lastName,String email, String password);
    void verifyAccountKey(String key);
    void updateLoginAttempt(String email, LoginType loginType);
}
