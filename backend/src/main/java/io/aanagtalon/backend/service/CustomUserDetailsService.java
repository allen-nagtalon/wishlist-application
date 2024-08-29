package io.aanagtalon.backend.service;

import io.aanagtalon.backend.domain.UserDetailModel;
import io.aanagtalon.backend.repo.CredentialRepo;
import io.aanagtalon.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CredentialRepo credentialRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " cannot be found"));
        var credentials = credentialRepo.getCredentialByUserEntityId(user.getId());

        return new UserDetailModel(user, credentials.orElseThrow());
    }
}
