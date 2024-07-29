package com.ex.initproj.DTO;

import com.ex.initproj.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Token {
    private String token;
    private Date end;
    private String employeeCode;
    public Token (String JWT, Date end, String employeeCode){
        this.end = end;
        this.token = JWT;
        this.employeeCode = employeeCode;
    }
}
