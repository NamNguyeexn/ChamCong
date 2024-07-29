package com.ex.initproj.DTO;

import com.ex.initproj.validations.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HumanInput {
    @NotBlank(message = "Name cant be null")
    private String name;
    @NotBlank(message = "Date of birth cant be null")
//    @ValidDateOfBirthInput
    private String dob;
    @NotBlank(message = "Address cant be null")
    private String address;
    @NotBlank(message = "Phone cant be null")
//    @ValidPhoneNumberCharacter
//    @ValidPhoneNumberLength
    private String phone;
    @NotBlank(message = "Username cant be null")
//    @ValidUsernameCharacter
//    @ValidUsernameLength
    private String username;
    @NotBlank(message = "Password cant be null")
//    @ValidPasswordCharacter
//    @ValidPasswordLength
    private String password;
}
