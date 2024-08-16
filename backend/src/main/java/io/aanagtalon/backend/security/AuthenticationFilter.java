package io.aanagtalon.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.aanagtalon.backend.dto.LoginRequest;
import io.aanagtalon.backend.dto.User;
import io.aanagtalon.backend.enumeration.LoginType;
import io.aanagtalon.backend.enumeration.TokenType;
import io.aanagtalon.backend.service.JwtService;
import io.aanagtalon.backend.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Map;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT;
import static io.aanagtalon.backend.constant.Constants.LOGIN_PATH;
import static io.aanagtalon.backend.domain.WishlistAuthentication.unauthenticated;
import static io.aanagtalon.backend.utils.RequestUtils.getResponse;
import static io.aanagtalon.backend.utils.RequestUtils.handleErrorResponse;
import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;

@Slf4j
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final UserService userService;
    private final JwtService jwtService;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        super(new AntPathRequestMatcher(LOGIN_PATH, POST.name()),authenticationManager);
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            var user = new ObjectMapper().configure(AUTO_CLOSE_JSON_CONTENT, true).readValue(request.getInputStream(), LoginRequest.class);
            userService.updateLoginAttempt("asayajurin07@gmail.com", LoginType.LOGIN_ATTEMPT);
            var authentication = unauthenticated(user.getEmail(), user.getPassword());
            return getAuthenticationManager().authenticate(authentication);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            handleErrorResponse(request, response, exception);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var user = (User) authResult.getPrincipal();
        userService.updateLoginAttempt(user.getEmail(), LoginType.LOGIN_SUCCESS);
        var httpResponse = sendResponse(request, response, user);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        var output = response.getOutputStream();
        var mapper = new ObjectMapper();
        mapper.writeValue(output, httpResponse);
        output.flush();
    }

    private Object sendResponse(HttpServletRequest request, HttpServletResponse response, User user) {
        jwtService.addCookie(response, user, TokenType.ACCESS);
        jwtService.addCookie(response, user, TokenType.REFRESH);
        return getResponse(request, Map.of("user", user), "Login Success", HttpStatus.OK);
    }
}
