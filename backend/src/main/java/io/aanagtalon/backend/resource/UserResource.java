package io.aanagtalon.backend.resource;

import io.aanagtalon.backend.domain.Response;
import io.aanagtalon.backend.domain.UserDetailModel;
import io.aanagtalon.backend.dto.LoginRequest;
import io.aanagtalon.backend.dto.UserRequest;
import io.aanagtalon.backend.service.AuthenticationService;
import io.aanagtalon.backend.service.JwtService;
import io.aanagtalon.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

import static io.aanagtalon.backend.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = { "/user" })
public class UserResource {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(@RequestBody @Valid UserRequest user, HttpServletRequest request) {
        userService.createUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return ResponseEntity.created(getUri()).body(getResponse(request, emptyMap(), "Account created. Check your email to enable your account", CREATED));
    }

    @GetMapping("/verify/account")
    public ResponseEntity<Response> verifyAccount(@RequestParam("key") String key, HttpServletRequest request) {
        userService.verifyAccountKey(key);
        return ResponseEntity.ok().body(getResponse(request, emptyMap(), "Account verified.", OK));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> authenticate(@RequestBody @Valid LoginRequest input, HttpServletRequest request) {
        UserDetailModel authUser = authService.authenticate(input);

        String jwtToken = jwtService.generateToken(authUser);

        return ResponseEntity.ok().body(getResponse(request, Map.of("token", jwtToken, "expiresIn", jwtService.getExpirationTime()), "Account logged in", OK));
    }

    @GetMapping
    public ResponseEntity<Response> getUser(@RequestHeader(name = "Authorization") String token, HttpServletRequest request) {
        var username = jwtService.extractUsername(token.substring(4));
        var user = userService.getUserByEmail(username);
        return ResponseEntity.ok().body(getResponse(request, Map.of("user", user), "User fetched from database", OK));
    }

    @GetMapping("/non-friends")
    public ResponseEntity<Response> getAllNonFriendedUsers(@RequestHeader(name = "Authorization") String token, HttpServletRequest request) {
        var id = jwtService.extractUserId(token.substring(4));
        return ResponseEntity.ok()
                .body(getResponse(request, emptyMap(), "All users fetched from database", OK));
    }

    @PutMapping("/follow/{id}")
    public ResponseEntity<Response> followUserById(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "id") Long recipientId, HttpServletRequest request) {
        var followerId = jwtService.extractUserId(token.substring(4));
        userService.followUserById(followerId, recipientId);
        return ResponseEntity.ok()
                .body(getResponse(request, emptyMap(), "User of ID " + followerId + " is now following user of ID " + recipientId, OK));
    }

    private URI getUri() {
        return URI.create("");
    }
}
