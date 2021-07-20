package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.AuthorizedUser;
import com.ruslanshakirov.crm.config.security.JwtUtils;
import com.ruslanshakirov.crm.dto.AuthResponse;
import com.ruslanshakirov.crm.dto.MessageResponse;
import com.ruslanshakirov.crm.dto.SignInRequest;
import com.ruslanshakirov.crm.dto.UserRequest;
import com.ruslanshakirov.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody SignInRequest signIn) {
        return authenticate(signIn.getEmail(), signIn.getPassword());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest) {
        boolean isEmailTaken = userService.isEmailTaken(userRequest.getEmail());
        if (isEmailTaken) {
            return ResponseEntity.badRequest().body(new MessageResponse("Пользователь с таким email уже существует."));
        }
        userService.create(userRequest);
        return ResponseEntity.ok(authenticate(userRequest.getEmail(), userRequest.getPassword()));
    }

    private AuthResponse authenticate(String email, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        var authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        AuthorizedUser authorizedUser = (AuthorizedUser) authentication.getPrincipal();
        return new AuthResponse(token, authorizedUser.getUser());
    }
}
