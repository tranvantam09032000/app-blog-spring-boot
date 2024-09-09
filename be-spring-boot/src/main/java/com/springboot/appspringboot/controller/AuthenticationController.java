package com.springboot.appspringboot.controller;

import com.springboot.appspringboot.dto.request.UserRequest;
import com.springboot.appspringboot.dto.response.TokenResponse;
import com.springboot.appspringboot.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AuthenticationController {

    private AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserRequest request) throws Exception {
        TokenResponse token = authenticationService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}
