package com.ex.initproj.services.impl;

import com.ex.initproj.models.Human;
import com.ex.initproj.repositories.CustomHumanRepository;
import com.ex.initproj.services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HumanServiceImpl implements HumanService {
    @Autowired
    private CustomHumanRepository customHumanRepository;
    @Override
    public Optional<Human> getHumanById(int id) {
        return customHumanRepository.getHumanById(id);
    }
}
