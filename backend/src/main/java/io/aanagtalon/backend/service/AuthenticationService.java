package io.aanagtalon.backend.service;

import io.aanagtalon.backend.domain.UserDetailModel;
import io.aanagtalon.backend.dto.LoginRequest;
import io.aanagtalon.backend.entity.exception.ApiException;
import io.aanagtalon.backend.repo.CredentialRepo;
import io.aanagtalon.backend.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepo userRepo;
    private final CredentialRepo credentialRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public UserDetailModel authenticate(LoginRequest input) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        var user = userRepo.findByEmailIgnoreCase(input.getUsername()).orElseThrow(() ->
                        new UsernameNotFoundException("Username cannot be found"));

        return new UserDetailModel(
            user, credentialRepo.getCredentialByUserEntityId(user.getId())
                .orElseThrow(() -> new ApiException("Credentials for user " + input.getUsername() + " not found")));
    }
}
