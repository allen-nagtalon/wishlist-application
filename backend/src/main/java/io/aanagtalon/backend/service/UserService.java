package io.aanagtalon.backend.service;

public interface UserService {
    void createUser(String firstName, String lastName,String email, String password);
    void verifyAccountKey(String key);
}
