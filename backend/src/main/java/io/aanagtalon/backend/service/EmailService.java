package io.aanagtalon.backend.service;

public interface EmailService {
    void sendNewAccountEmail(String name, String email, String token);
}
