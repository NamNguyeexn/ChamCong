package com.ex.initproj.services;

import com.ex.initproj.DTO.HumanInput;
import com.ex.initproj.models.Human;
import com.ex.initproj.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<Human> getHumanByUsername(String username);
    Optional<User> getUserByUsername(String username);
    Optional<User> saveNewUser(HumanInput humanInput);
    Optional<Human> getHumanByPhone(String phone);
}
