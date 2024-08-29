package io.aanagtalon.backend.domain;

import io.aanagtalon.backend.entity.CredentialEntity;
import io.aanagtalon.backend.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailModel implements UserDetails {
    private final String email;
    private final String password;

    public UserDetailModel(UserEntity user, CredentialEntity credential) {
        this.email = user.getEmail();
        this.password = credential.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
