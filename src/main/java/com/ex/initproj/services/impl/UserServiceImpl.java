package com.ex.initproj.services.impl;

import com.ex.initproj.DTO.HumanInput;
import com.ex.initproj.mapper.UserMapper;
import com.ex.initproj.models.Human;
import com.ex.initproj.models.Role;
import com.ex.initproj.models.User;
import com.ex.initproj.repositories.CustomHumanRepository;
import com.ex.initproj.repositories.CustomUserRepository;
import com.ex.initproj.services.UserService;
import com.ex.initproj.utils.ConvertData;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private CustomHumanRepository customHumanRepository;
    @Autowired
    private CustomUserRepository customUserRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Optional<Human> getHumanByUsername(String username) {
        Optional<User> user = customUserRepository.getUserByUsername(username);
        if (user.isEmpty()){
            log.info("USER DAO - GET HUMAN BY USERNAME - NULL USER");
            return Optional.empty();
        } else {
            Optional<Human> human = customHumanRepository.getHumanById(user.get().getHumanid());
            if (human.isEmpty()){
                log.info("USER DAO - GET HUMAN BY USERNAME - NULL HUMAN");
                return Optional.empty();
            } else {
                return human;
            }
        }
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return customUserRepository.getUserByUsername(username);
    }
    @Transactional
    @Override
    public Optional<User> saveNewUser(HumanInput humanInput) {
        customHumanRepository.saveHuman(humanInput, Date.valueOf(humanInput.getDob()));
        Optional<Human> human = customHumanRepository.getHumanByPhone(humanInput.getPhone());
        try {
            if(human.isEmpty()){
                log.info("========================================");
                log.info("USER SERVICE - SAVE NEW USER - NOT FOUND HUMAN");
                return Optional.empty();
            }
            log.info("USER SERVICE - SAVE NEW USER - FOUND HUMAN");
            String role = Role.USER.name();
            String employeeCode = ConvertData.convertEmployeeCode(role, String.valueOf(human.get().getId()));
            customUserRepository.saveUser(userMapper.inputRegisterToUser(humanInput, human.get().getId(), role, employeeCode));
            log.info("USER SERVICE - SAVE NEW USER - SUCCESS");
            return customUserRepository.getUserByUsername(humanInput.getUsername());
        } catch (NoResultException n){
            log.info("USER SERVICE - SAVE NEW USER - EXCEPTION : " + n.getMessage());
            customHumanRepository.deleteHumanById(human.get().getId());
            return Optional.empty();
        } catch (Exception e) {
            log.info("USER SERVICE - SAVE NEW USER - EXCEPTION : " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Human> getHumanByPhone(String phone) {
        return customHumanRepository.getHumanByPhone(phone);
    }
}

