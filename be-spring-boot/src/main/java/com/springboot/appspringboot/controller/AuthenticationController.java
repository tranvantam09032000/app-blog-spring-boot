package com.springboot.appspringboot.controller;
import com.nimbusds.jose.JOSEException;
import com.springboot.appspringboot.dto.request.AuthenticationRequestDTO;
import com.springboot.appspringboot.dto.request.RefreshTokenRequestDTO;
import com.springboot.appspringboot.dto.response.AuthenticationResponseDTO;
import com.springboot.appspringboot.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthorService authorService;

    @CrossOrigin
    @PostMapping("/login")
    AuthenticationResponseDTO loginAuthor(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        return authorService.loginAuthor(authenticationRequestDTO);
    }

    @CrossOrigin
    @PostMapping("/refresh")
    AuthenticationResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO request)
            throws ParseException, JOSEException {
        return authorService.refreshToken(request);
    }

}