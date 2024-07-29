package com.ex.initproj.repositories;

import com.ex.initproj.DTO.HumanInput;
import com.ex.initproj.mapper.HumanMapper;
import com.ex.initproj.models.Human;
import com.ex.initproj.repositories.JPARepository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;
@Repository
public class CustomHumanRepository {
    @Autowired
    private HumanRepository humanRepository;
    @Autowired
    private HumanMapper humanMapper;
    public Optional<Human> getHumanById(int id) {
        return humanRepository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get("id"), id)
        );
    }
    public Optional<Human> getHumanByPhone(String phone){
        return humanRepository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get("phone"), phone)
        );
    }
    @Transactional
    public void saveHuman(HumanInput humanInput, Date dob) {
        humanRepository.save(humanMapper.humanInputToHuman(humanInput, dob));
    }
    public void deleteHumanById(int id){
        humanRepository.delete(
                (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("id"), id)
        );
    }
}
