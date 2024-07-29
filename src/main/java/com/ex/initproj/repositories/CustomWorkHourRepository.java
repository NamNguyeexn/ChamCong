package com.ex.initproj.repositories;

import com.ex.initproj.models.WorkHour;
import com.ex.initproj.repositories.JPARepository.WorkHourRepository;
import com.ex.initproj.utils.GenerateWorkHourCode;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomWorkHourRepository {
    @Autowired
    private WorkHourRepository workHourRepository;
    public Optional<WorkHour> getWorkHourById(int id){
        return workHourRepository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get("id"), id)
        );
    }
    public Optional<List<WorkHour>> getListWorkHourByUserId(int userid){
        return Optional.of(workHourRepository.findAll(
            (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userid"), userid)
        ));
    }
    public Optional<WorkHour> getCheckInByNote(LocalDateTime start, int userid){
        return workHourRepository.findOne(
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get("note"),
                        GenerateWorkHourCode.generateWorkHourCode(start, userid))
        );
    }
    public Optional<WorkHour> getLastWorkHour(int userId) {
//        return workHourRepository.findOne(
//                (root, query, criteriaBuilder) -> {
//                    Predicate condition = criteriaBuilder.equal(root.get("userid"), userId);
//                    query.orderBy(criteriaBuilder.desc(root.get("id")));
//                    return condition;
//                }
//        );
        Optional<List<WorkHour>> workHours = getListWorkHourByUserId(userId);
        if (workHours.get().isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(workHours.get().get(workHours.get().size() - 1));
        }
    }
    public void saveCheckIn(WorkHour workHour) {
        workHourRepository.save(workHour);
    }
    public void deleteWorkHourById(int id) {
        workHourRepository.delete(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id)
        );
    }
    public void updateWorkHourCheckout(WorkHour workHour) {
        workHourRepository.save(workHour);
    }
}
