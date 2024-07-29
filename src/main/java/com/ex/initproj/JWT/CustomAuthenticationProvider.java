package com.ex.initproj.JWT;

import com.ex.initproj.models.User;
import com.ex.initproj.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Optional;
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider, Serializable {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            log.info("=============================================");
            log.info("CUSTOM AUTHENTICATION PROVIDER - AUTHENTICATE");
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();
            Optional<User> user = userService.getUserByUsername(name);
            if(user.isEmpty()){
                log.info("------------------------------------------------");
                log.info("CUSTOM AUTHENTICATION PROVIDER - AUTHENTICATE - NULL USER");
                return null;
            }
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                log.info("------------------------------------------------");
                log.info("CUSTOM AUTHENTICATION PROVIDER - AUTHENTICATE - FOUND USER");
                return new PreAuthenticatedAuthenticationToken(
                        user, password, new ArrayList<>());
            } else {
                log.info("------------------------------------------------");
                log.info("CUSTOM AUTHENTICATION PROVIDER - AUTHENTICATE - WRONG PASSWORD");
                return null;
            }
        } catch (Exception e) {
            log.info("------------------------------------------------");
            log.info("CUSTOM AUTHENTICATION PROVIDER - AUTHENTICATE - SERVER ERROR");
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
