package me.quadradev.auth.infrastructure.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    void generateAndValidateToken() {
        String token = jwtProvider.generateToken("subject", 1L, List.of("ROLE_USER"));
        assertTrue(jwtProvider.validateToken(token));
        assertEquals("subject", jwtProvider.getSubject(token));
    }
}
