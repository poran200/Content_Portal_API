package com.gft.manager.dto;

import com.gft.manager.validation.annotation.NullOrNotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginDto {
    @NullOrNotBlank(message = "Login Username or Email can be null but not blank")
   private String  email;
    @NotNull(message = "Login password cannot be blank")
   private String password;
}
