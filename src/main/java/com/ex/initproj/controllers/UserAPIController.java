package com.ex.initproj.controllers;

//import com.ex.initproj.DTO.ResponseObject;
//import com.ex.initproj.DTO.UserInput;
import com.ex.initproj.JWT.JwtTokenService;
import com.ex.initproj.models.User;
import com.ex.initproj.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/api")
public class UserAPIController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenService jwtTokenService;
    @GetMapping("/user/home")
    public ResponseEntity<Map<String, User>> getHomepage(HttpServletRequest request) {
        log.info("=============================");
        log.info("USER API CONTROLLER - GET HOMEPAGE");
        Map<String, User> response = new HashMap<>();
        String username = jwtTokenService.getUsername(
                request
                        .getHeader(HttpHeaders.AUTHORIZATION)
                        .split(" ")[1].trim()
        );
        try {
            Optional<User> user = userService.getUserByUsername(username);
            if(user.isPresent()) {
                log.info("--------------------------------------------");
                log.info("USER API CONTROLLER - GET HOMEPAGE - FOUND USER");
                response.put("USER API CONTROLLER - GET HOMEPAGE - FOUND USER", user.get());
                return ResponseEntity.ok().body(response);
            }
            else {
                log.info("--------------------------------------------");
                String message = "USER API CONTROLLER - GET HOMEPAGE - NULL USER \n "
                        + "USERNAME : "
                        + username;
                log.info(message);
                response.put(message, null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            log.info("----------------------------------------------");
            log.info("USER API CONTROLLER - GET HOMEPAGE - SERVER ERROR");
            response.put("USER API CONTROLLER - GET HOMEPAGE - SERVER ERROR", null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
