package io.aanagtalon.backend.service;

import io.aanagtalon.backend.domain.UserDetailModel;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    Long extractUserId(String token);
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDetailModel userDetails);
    String generateToken(Map<String, Object> extraClaims, UserDetailModel userDetails);
    long getExpirationTime();
    boolean isTokenValid(String token, UserDetails userDetails);
}
