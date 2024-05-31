package com.employee.Employee.Management.Portal.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtTokenValidatorTest {

    private JwtTokenValidator jwtTokenValidator;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    private static final String SECRET_KEY = "wpembytrwcvnryxksdbqwjebruyGHyudqgwveytrtrCSnwifoesarjbwe";
    private static final String JWT_HEADER = "Authorization";
    private SecretKey key;

    @BeforeEach
    void setUp() {
        jwtTokenValidator = new JwtTokenValidator();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        JwtConstant.SECRET_KEY = SECRET_KEY;
        JwtConstant.JWT_HEADER = JWT_HEADER;

        // Clear the SecurityContextHolder before each test
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() {
        // Clear the SecurityContextHolder after each test
        SecurityContextHolder.clearContext();
    }

    @Test
    void testValidToken() throws ServletException, IOException {
        String email = "user@example.com";
        String authorities = "ROLE_USER";
        String token = createToken(email, authorities);
        String jwt = "Bearer " + token;

        when(request.getHeader(JWT_HEADER)).thenReturn(jwt);

        jwtTokenValidator.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(email, SecurityContextHolder.getContext().getAuthentication().getName());
        assertEquals(1, SecurityContextHolder.getContext().getAuthentication().getAuthorities().size());

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testInvalidToken() throws ServletException, IOException {
        String jwt = "Bearer invalidToken";

        when(request.getHeader(JWT_HEADER)).thenReturn(jwt);

        assertThrows(BadCredentialsException.class, () -> {
            jwtTokenValidator.doFilterInternal(request, response, filterChain);
        });

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void testNoToken() throws ServletException, IOException {
        when(request.getHeader(JWT_HEADER)).thenReturn(null);

        jwtTokenValidator.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    private String createToken(String email, String authorities) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", email)
                .claim("authorities", authorities)
                .signWith(key)
                .compact();
    }
}
