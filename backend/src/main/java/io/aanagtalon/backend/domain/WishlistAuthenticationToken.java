package io.aanagtalon.backend.domain;

import io.aanagtalon.backend.dto.User;
import io.aanagtalon.backend.entity.exception.WishlistException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@SuppressWarnings("LombokGetterMayBeUsed")
public class WishlistAuthenticationToken extends AbstractAuthenticationToken {
    private static final String PASSWORD_PROTECTED = "[PASSWORD PROTECTED]";
    private static final String EMAIL_PROTECTED = "[EMAIL PROTECTED]";
    private User user;
    private String email;
    private String password;
    private boolean authenticated;

    private WishlistAuthenticationToken(String email, String password) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.email = email;
        this.password = password;
        this.authenticated = false;
    }

    private WishlistAuthenticationToken(User user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.user = user;
        this.password = PASSWORD_PROTECTED;
        this.email = EMAIL_PROTECTED;
        this.authenticated = true;
    }

    public static WishlistAuthenticationToken unauthenticated(String email, String password) {
        return new WishlistAuthenticationToken(email, password);
    }

    public static WishlistAuthenticationToken authenticated(User user, Collection<? extends GrantedAuthority> authorities) {
        return new WishlistAuthenticationToken(user, authorities);
    }

    @Override
    public Object getCredentials() {
        return PASSWORD_PROTECTED;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new WishlistException("You cannot manually set authentication");
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}
