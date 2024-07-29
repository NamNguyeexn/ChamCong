package com.ex.initproj.services;

import com.ex.initproj.models.Human;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface HumanService {
    Optional<Human> getHumanById(int id);
}
