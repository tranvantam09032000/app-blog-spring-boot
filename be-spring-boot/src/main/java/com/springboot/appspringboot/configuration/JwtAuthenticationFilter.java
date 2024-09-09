package com.springboot.appspringboot.configuration;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                SignedJWT signedJWT = SignedJWT.parse(token);
                JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

                if (isTokenValid(claims)) {
                    String username = claims.getSubject();
                    PreAuthenticatedAuthenticationToken authenticationToken =
                            new PreAuthenticatedAuthenticationToken(username, null, null);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            } catch (ParseException e) {
                e.printStackTrace(); // Xử lý lỗi token không hợp lệ
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isTokenValid(JWTClaimsSet claims) {
        try {
            Date expirationTime = claims.getExpirationTime();
            if (expirationTime == null || expirationTime.before(Date.from(Instant.now()))) {
                return false;
            }

            String issuer = claims.getIssuer();
            if (!"tranvantam".equals(issuer)) {
                return false;
            }

//            List<String> audience = claims.getAudience();
//            if (audience == null || !audience.contains("your-audience")) {
//                return false;
//            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

