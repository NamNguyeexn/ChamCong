package com.ex.initproj.controllers;

import com.ex.initproj.DTO.HumanInput;
import com.ex.initproj.DTO.HumanOutput;
import com.ex.initproj.DTO.Token;
import com.ex.initproj.DTO.UserInput;
import com.ex.initproj.JWT.JwtTokenService;
import com.ex.initproj.mapper.HumanMapper;
import com.ex.initproj.models.Human;
import com.ex.initproj.models.User;
import com.ex.initproj.services.HumanService;
import com.ex.initproj.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Tag(name = "Login")
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginAPIController {
    @Autowired
    private UserService userService;
    @Autowired
    private HumanService humanService;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
    private final HumanMapper humanMapper;

    public LoginAPIController(HumanMapper humanMapper) {
        this.humanMapper = humanMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserInput userInput){
        Map<String, User> response = new HashMap<>();
        try {
            Optional<User> user = userService.getUserByUsername(userInput.getUsername());
            if(user.isEmpty()) {
                log.info("-----------------------------------");
                log.info("LOGIN API CONTROLLER - LOGIN - NOT FOUND USER");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else if (passwordEncoder.matches(userInput.getPassword(), user.get().getPassword())){
                log.info("-----------------------------------");
                log.info("LOGIN API CONTROLLER - LOGIN - FOUND USER");
                Optional<Human> human = humanService.getHumanById(user.get().getHumanid());
                if(human.isEmpty()){
                    log.info("-----------------------------------");
                    log.info("LOGIN API CONTROLLER - LOGIN - NOT FOUND HUMAN");
                    response.put("LOGIN API CONTROLLER - LOGIN - NOT FOUND HUMAN", null);
                    return ResponseEntity.badRequest().body(response);
                } else {
                    String JWT = this.jwtTokenService.generateAccessToken(user.get());
                    Token token = new Token(JWT, new Date(System.currentTimeMillis() + (1000*60*8)), human.get().getName());
                    log.info("OUTPUT TOKEN : " + token + " USER : " + user.get().getEmployeeCode());
                    response.put(JWT, user.get());
                    return ResponseEntity.status(HttpStatus.OK).body(JWT);
                }
            } else {
                log.info("-----------------------------------");
                log.info("LOGIN API CONTROLLER - LOGIN - WRONG PASSWORD");
//                log.info("PASSWORD INPUT : " + userInput.getPassword());
//                log.info("PASSWORD INPUT ENCODED : " + passwordEncoder.encode(userInput.getPassword()));
//                log.info("PASSWORD IN DATABASE : " + user.get().getPassword());
//                log.info("MATCH ? : " + passwordEncoder.matches(userInput.getPassword(), user.get().getPassword()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.info("-----------------------------------");
            log.info("LOGIN API CONTROLLER - LOGIN - EXCEPTION " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, HumanOutput>> register(@Valid @RequestBody HumanInput humanInput){
        Map<String, HumanOutput> response = new HashMap<>();
        try {
            Optional<Human> human = userService.getHumanByPhone(humanInput.getPhone());
            Optional<User> userCheck = userService.getUserByUsername(humanInput.getUsername());
            if(human.isPresent()){
                log.info("-----------------------------------");
                log.info("LOGIN API CONTROLLER - REGISTER - USE PHONE NUMBER EXITS");
                response.put("LOGIN API CONTROLLER - REGISTER - USE PHONE NUMBER EXITS", null);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
            } else if(userCheck.isPresent()){
                log.info("-----------------------------------");
                log.info("LOGIN API CONTROLLER - REGISTER - USERNAME EXITS");
                response.put("LOGIN API CONTROLLER - REGISTER - USERNAME EXITS", null);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
            } else {
                String password = humanInput.getPassword();
                log.info("PASSWORD BEFORE ENCODE : " + password);
                humanInput.setPassword(passwordEncoder.encode(humanInput.getPassword()));
                Optional<User> user = userService.saveNewUser(humanInput);
                if (user.isEmpty()){
                    log.info("----------------------------------");
                    log.info("LOGIN API CONTROLLER - REGISTER - CANT REGISTER");
                    response.put("LOGIN API CONTROLLER - REGISTER - CANT REGISTER", null);
                    return ResponseEntity.badRequest().body(response);
                }
                log.info("-----------------------------------");
                log.info("LOGIN API CONTROLLER - REGISTER - SUCCESS");
                HumanOutput humanOutput = humanMapper.userToHumanOutput(user.get(), humanInput, password);
                //
                response.put("LOGIN API CONTROLLER - REGISTER - SUCCESS ", humanOutput);
                return ResponseEntity.ok().body(response);
            }

        } catch (Exception e){
            response.put("LOGIN API CONTROLLER - REGISTER - EXCEPTION : " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
