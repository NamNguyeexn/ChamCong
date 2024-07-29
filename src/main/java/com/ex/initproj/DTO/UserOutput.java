package com.ex.initproj.DTO;

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
public class UserOutput {
    @NotBlank(message = "Username cant be null")
//    @ValidUsernameCharacter
//    @ValidUsernameLength
    private String username;
}
