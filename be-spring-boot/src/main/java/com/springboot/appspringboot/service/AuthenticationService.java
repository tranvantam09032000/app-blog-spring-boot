package com.springboot.appspringboot.service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.springboot.appspringboot.dto.response.TokenResponse;
import com.springboot.appspringboot.model.User;
import com.springboot.appspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @NonFinal
    @Value("${jwt.secret}")
    private String jwtSecret;

    public TokenResponse login(String email, String password) throws Exception {
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("Invalid username or password"));
        boolean authenticated = passwordEncoder.matches(password, user.getPassword());
        if(!authenticated) {
            throw new RuntimeException("Invalid username or password");
        }
        TokenResponse token = new TokenResponse();
        token.setToken(generateToken(user));
        return token;
    }

    private String generateToken(User user) throws Exception {
        try {
            JWSSigner signer = new MACSigner(jwtSecret);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getEmail())
                    .issuer("tranvantam")
                    .expirationTime(new Date(new Date().getTime() + 3600 * 1000))
                    .claim("scope", user.getRole())
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet
            );

            signedJWT.sign(signer);

            return signedJWT.serialize();
        }catch (AuthenticationException e){
            throw new RuntimeException("Invalid username or password");
        }

    }
}
