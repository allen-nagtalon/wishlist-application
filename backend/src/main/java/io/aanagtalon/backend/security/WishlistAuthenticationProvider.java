package io.aanagtalon.backend.security;

import io.aanagtalon.backend.domain.UserPrincipal;
import io.aanagtalon.backend.domain.WishlistAuthentication;
import io.aanagtalon.backend.entity.exception.WishlistException;
import io.aanagtalon.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

import static io.aanagtalon.backend.domain.WishlistAuthentication.*;

@Component
@RequiredArgsConstructor
public class WishlistAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var apiAuthentication = authenticationFunction.apply(authentication);
        var user = userService.getUserByEmail(apiAuthentication.getEmail());
        if(user != null) {
            var userCredential = userService.getUserCredentialById(user.getId());
            var userPrincipal = new UserPrincipal(user, userCredential);
            validAccount.accept(userPrincipal);
            if(encoder.matches(apiAuthentication.getPassword(), userCredential.getPassword())) {
                return authenticated(user, userPrincipal.getAuthorities());
            } else throw new BadCredentialsException("Email and/or password incorrect. Please try again");
        } else throw new WishlistException("Unable to authentication");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WishlistAuthentication.class.isAssignableFrom(authentication);
    }

    private final Function<Authentication, WishlistAuthentication> authenticationFunction = authentication -> (WishlistAuthentication) authentication;

    private final Consumer<UserPrincipal> validAccount = userPrincipal -> {
        if(userPrincipal.isAccountNonLocked()) { throw new LockedException("Your account is currently locked"); }
        if(userPrincipal.isEnabled()) { throw new DisabledException("Your account is currently not enabled"); }
        if(userPrincipal.isAccountNonExpired()) { throw new DisabledException("Your account has expired. Please contact admin"); }
    };
}
