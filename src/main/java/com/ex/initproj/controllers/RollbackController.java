package com.ex.initproj.controllers;

import com.ex.initproj.JWT.JwtTokenService;
import com.ex.initproj.models.User;
import com.ex.initproj.services.UserService;
import com.ex.initproj.services.WorkHourService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/fullAccess")
public class RollbackController {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private WorkHourService workHourService;
    @Autowired
    private UserService userService;
    @DeleteMapping("/deleteWorkHourById/{id}")
    public ResponseEntity<String> deleteWorkHourById(@RequestParam int id, HttpServletRequest request){
        return ResponseEntity.ok().body(workHourService.deleteWorkHourById(id));
    }
    @DeleteMapping("/deleteAllWorkHour")
    public ResponseEntity<String> deleteAllWorkHour(HttpServletRequest request){
        String username = jwtTokenService.getUsername(
                request
                        .getHeader(HttpHeaders.AUTHORIZATION)
                        .split(" ")[1].trim()
        );
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND USER");
        }
        workHourService.getAllWorkHourByUsername(user.get()).ifPresent(
                workHours -> workHours.forEach(
                        workHour -> workHourService.deleteWorkHourById(workHour.getId())
                )
        );
        return ResponseEntity.ok().body("Success");
    }
}
