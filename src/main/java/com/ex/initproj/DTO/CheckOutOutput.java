package com.ex.initproj.DTO;

import com.ex.initproj.models.Status;
import com.ex.initproj.validations.ValidCheckOutTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutOutput {
    private LocalDateTime start;
//    @ValidCheckOutTime
    private LocalDateTime end;
    private String employeeCode;
    private Status status;
    private String note;
}
