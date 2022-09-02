package com.gft.manager.security;

import com.gft.manager.model.User;
import com.gft.manager.model.CustomUserDetails;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class JwtTokenProviderTest {

    private static final String jwtSecret = "testSecret";
    private static final long jwtExpiryInMs = 2500;

    private JwtTokenProvider tokenProvider;



    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.tokenProvider = new JwtTokenProvider(jwtSecret, jwtExpiryInMs);
    }

    @Test
    public void testGetUserIdFromJWT() {
        String token = tokenProvider.generateToken(stubCustomUser());
        assertEquals(100, tokenProvider.getUserIdFromJWT(token));
    }

    @Test
    public void testGetTokenExpiryFromJWT() {
        String token = tokenProvider.generateTokenFromUserId(120L+"");
        assertNotNull(tokenProvider.getTokenExpiryFromJWT(token));
    }

    @Test
    public void testGetExpiryDuration() {
        assertEquals(jwtExpiryInMs, tokenProvider.getExpiryDuration());
    }

    private CustomUserDetails stubCustomUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        return new CustomUserDetails(user);
    }
}
