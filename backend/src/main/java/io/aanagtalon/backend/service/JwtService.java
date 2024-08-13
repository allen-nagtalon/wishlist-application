package io.aanagtalon.backend.service;

import io.aanagtalon.backend.domain.Token;
import io.aanagtalon.backend.domain.TokenData;
import io.aanagtalon.backend.dto.User;
import io.aanagtalon.backend.enumeration.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;
import java.util.function.Function;

public interface JwtService {
    String createToken(User user, Function<Token, String> tokenFunction);
    Optional<String> extractToken(HttpServletRequest request, String tokenType);
    void addCookie(HttpServletResponse response, User user, TokenType type);
    <T> T getTokenData(String token, Function<TokenData, T> tokenFunction);
}
