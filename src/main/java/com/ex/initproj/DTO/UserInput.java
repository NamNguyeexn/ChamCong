package com.ex.initproj.DTO;

import com.ex.initproj.validations.ValidPasswordCharacter;
import com.ex.initproj.validations.ValidPasswordLength;
import com.ex.initproj.validations.ValidUsernameCharacter;
import com.ex.initproj.validations.ValidUsernameLength;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {
    @NotBlank(message = "Username cant be null")
//    @ValidUsernameCharacter
//    @ValidUsernameLength
    private String username;
    @NotBlank(message = "Password cant be null")
//    @ValidPasswordCharacter
//    @ValidPasswordLength
    private String password;
}
