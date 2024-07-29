package com.ex.initproj.repositories;

import com.ex.initproj.models.User;
import com.ex.initproj.repositories.JPARepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class CustomUserRepository {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByUsername(String username){
        return userRepository.findOne(
            (root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("username"), username)
        );
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
}
