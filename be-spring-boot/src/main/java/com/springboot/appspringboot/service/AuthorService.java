package com.springboot.appspringboot.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.springboot.appspringboot.dto.request.AuthenticationRequestDTO;
import com.springboot.appspringboot.dto.request.AuthorRequestDTO;
import com.springboot.appspringboot.dto.response.AuthenticationResponseDTO;
import com.springboot.appspringboot.entity.Author;
import com.springboot.appspringboot.exception.AppException;
import com.springboot.appspringboot.exception.ErrorCode;
import com.springboot.appspringboot.mapper.AuthorMapper;
import com.springboot.appspringboot.repository.AuthorRepository;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public List<Author> getAuthor() {
        return authorRepository.findAll();
    }

    public Author createAuthor(AuthorRequestDTO request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Author author = this.authorMapper.authorRequestToAuthor(request, passwordEncoder.encode(request.getPassword()));
        authorRepository.save(author);
        return author;
    }

    public AuthenticationResponseDTO loginAuthor(AuthenticationRequestDTO request) {
        Author author = this.authorRepository.findAuthorByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), author.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return AuthenticationResponseDTO.builder()
                .token(generateToken(request.getEmail()))
                .build();
    }

    private String generateToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("tranvantam")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("custom", "custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
