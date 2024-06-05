package com.employee.Employee.Management.Portal.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtProviderTest {

    private JwtProvider jwtProvider;
    private SecretKey key;

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider();
        key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    }

    @Test
    void testGenerateToken() {
        // Arrange
        Authentication auth = mock(Authentication.class);
        GrantedAuthority authority = mock(GrantedAuthority.class);
        Collection<GrantedAuthority> authorities = List.of(authority);

        when(auth.getAuthorities()).thenAnswer(invocation -> authorities);
        when(auth.getName()).thenReturn("user@example.com");
        when(authority.getAuthority()).thenReturn("ROLE_USER");

        // Act
        String token = jwtProvider.generateToken(auth);

        // Assert
        assertNotNull(token);
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        assertEquals("user@example.com", claims.get("email"));
        assertEquals("ROLE_USER", claims.get("authorities"));
    }

    @Test
    void testGetEmailFromJwtToken() {
        // Arrange
        String email = "user@example.com";
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", email)
                .signWith(key)
                .compact();
        token = "Bearer " + token; // simulate the prefix

        // Act
        String extractedEmail = jwtProvider.getEmailFromJwtToken(token);

        // Assert
        assertEquals(email, extractedEmail);
    }

    @Test
    void testPopulateAuthorities() {
        // Arrange
        GrantedAuthority authority1 = mock(GrantedAuthority.class);
        GrantedAuthority authority2 = mock(GrantedAuthority.class);
        when(authority1.getAuthority()).thenReturn("ROLE_USER");
        when(authority2.getAuthority()).thenReturn("ROLE_ADMIN");
        Collection<GrantedAuthority> authorities = List.of(authority1, authority2);

        // Act
        String roles = jwtProvider.populateAuthorities(authorities);

        // Assert
        assertNotNull(roles);
        assertTrue(roles.contains("ROLE_USER"));
        assertTrue(roles.contains("ROLE_ADMIN"));
    }
}
