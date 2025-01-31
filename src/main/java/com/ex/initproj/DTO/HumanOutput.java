package com.ex.initproj.DTO;

import com.ex.initproj.validations.ValidPasswordCharacter;
import com.ex.initproj.validations.ValidPasswordLength;
import com.ex.initproj.validations.ValidUsernameCharacter;
import com.ex.initproj.validations.ValidUsernameLength;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HumanOutput {
    private String employeeCode;
    private String name;
//    @ValidUsernameCharacter
//    @ValidUsernameLength
    private String username;
//    @ValidPasswordCharacter
//    @ValidPasswordLength
    private String password;
}
