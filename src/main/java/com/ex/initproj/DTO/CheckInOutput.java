package com.ex.initproj.DTO;

import com.ex.initproj.models.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class CheckInOutput {
    private LocalDateTime start;
    private String employeeCode;
    private Status status;
}
