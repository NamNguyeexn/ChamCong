package com.ex.initproj.models;

import com.ex.initproj.validations.ValidDateOfBirth;
import com.ex.initproj.validations.ValidDateOfBirthInput;
import com.ex.initproj.validations.ValidPhoneNumberCharacter;
import com.ex.initproj.validations.ValidPhoneNumberLength;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "human")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
//    @ValidDateOfBirth
    private Date dob;
    private String address;
//    @ValidPhoneNumberCharacter
//    @ValidPhoneNumberLength
    private String phone;
}
