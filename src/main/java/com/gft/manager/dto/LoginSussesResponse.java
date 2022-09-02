package com.gft.manager.dto;

import com.gft.manager.model.payload.JwtAuthenticationResponse;
import lombok.Data;

@Data
public class LoginSussesResponse {
    UserResponse userResponse;
    JwtAuthenticationResponse jwtToken;

    public LoginSussesResponse(UserResponse userResponse, JwtAuthenticationResponse jwtToken) {
        this.userResponse = userResponse;
        this.jwtToken = jwtToken;
    }
}
