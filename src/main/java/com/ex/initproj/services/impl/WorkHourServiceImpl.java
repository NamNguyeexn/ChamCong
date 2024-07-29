package com.ex.initproj.services.impl;

import com.ex.initproj.DTO.CheckInOutput;
import com.ex.initproj.DTO.CheckOutOutput;
import com.ex.initproj.mapper.WorkHourMapper;
import com.ex.initproj.models.Status;
import com.ex.initproj.models.User;
import com.ex.initproj.models.WorkHour;
import com.ex.initproj.repositories.CustomWorkHourRepository;
import com.ex.initproj.services.WorkHourService;
import com.ex.initproj.utils.GenerateWorkHourCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class WorkHourServiceImpl implements WorkHourService {
    @Autowired
    private WorkHourMapper workHourMapper;
    @Autowired
    private CustomWorkHourRepository customWorkHourRepository;
    @Override
    public Optional<List<WorkHour>> getAllWorkHourByUsername(User user) {
        return customWorkHourRepository.getListWorkHourByUserId(user.getId());
    }

    @Override
    public Optional<CheckInOutput> checkin(User user) {
        Optional<WorkHour> workHour = customWorkHourRepository.getLastWorkHour(user.getId());
        LocalDateTime start = LocalDateTime.now();
        if(workHour.isEmpty()){
            log.info("WORK HOUR SERVICE - CHECK IN - NULL WORK HOUR");
//            return Optional.empty();
        } else if(workHour.get().getStart().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())) {
            log.info("WORK HOUR SERVICE - CHECK IN - CANT CHECK IN IN SAME DAY");
            return Optional.empty();
        }
        Optional<WorkHour> workHourOut = updateCheckIn(start, user);
        if (workHourOut.isEmpty()){
            log.info("WORK HOUR SERVICE - CHECK IN - WORK HOUR UPDATE NULL");
            return Optional.empty();
        }
        CheckInOutput checkInOutput = workHourMapper.workHourToCheckInOutPut(workHour.get(), user);

        return Optional.of(checkInOutput);
    }
    @Override
    public Optional<CheckOutOutput> checkout(LocalDateTime localDateTime, User user) {
        Optional<WorkHour> workHour = customWorkHourRepository.getLastWorkHour(user.getId());
        if(workHour.isEmpty()) {
            log.info("WORK HOUR SERVICE - CHECK OUT - NULL CHECKED IN");
            return Optional.empty();
        } else if (!workHour.get().getStart().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())) {
            log.info("WORK HOUR SERVICE - CHECK OUT - WRONG DATE");
            return Optional.empty();
        } else if (Duration.between(workHour.get().getStart(), LocalDateTime.now()).toHours() < 8) {
            log.info("WORK HOUR SERVICE - CHECK OUT - NOT ENOUGH HOUR");
            return Optional.empty();
        } else {
            log.info("WORK HOUR SERVICE - CHECK OUT - GOT CHECKED IN");
            int id = workHour.get().getId();
            workHour.get().setId(id);
            workHour.get().setStatus(Status.DONE);
            workHour.get().setNote("done");
            workHour.get().setEnd(LocalDateTime.now());
            customWorkHourRepository.updateWorkHourCheckout(workHour.get());
            Optional<WorkHour> workHourOut = customWorkHourRepository.getWorkHourById(id);
            if(workHourOut.isEmpty()){
                log.info("WORK HOUR SERVICE - CHECK OUT - WORK HOUR NULL");
                return Optional.empty();
            }
            // viet mapper
            CheckOutOutput checkOutOutput = workHourMapper.workHourToCheckOutOutput(workHour.get(), user);
            return Optional.of(checkOutOutput);
        }
    }

    @Override
    public Optional<WorkHour> testGetLastWorkHour(User user) {
        Optional<WorkHour> workHour = customWorkHourRepository.getLastWorkHour(user.getId());
        if (workHour.isEmpty()){
            log.info("WORK HOUR DAO - GET LAST WORK HOUR - NULL CHECKED IN");
            return Optional.empty();
        } else return workHour;
    }

    @Override
    public String deleteWorkHourById(int id) {
        Optional<WorkHour> workHour = customWorkHourRepository.getWorkHourById(id);
        if(workHour.isEmpty()) {
            return "CANT FIND WORK HOUR";
        } else {
            customWorkHourRepository.deleteWorkHourById(workHour.get().getId());
            return "SUCCESS";
        }
    }

    private Optional<WorkHour> updateCheckIn(LocalDateTime start, User user){
        LocalDateTime end = LocalDateTime.now();
        String status = String.valueOf(Status.NOTDONE);
        String note = GenerateWorkHourCode.generateWorkHourCode(start, user.getId());
        WorkHour workHour = new WorkHour();
        workHour.setUserid(user.getId());
        workHour.setStart(start);
        workHour.setEnd(end);
        workHour.setStatus(Status.valueOf(status));
        workHour.setNote(note);
        customWorkHourRepository.saveCheckIn(workHour);
        return customWorkHourRepository.getCheckInByNote(start, user.getId());
    }
}
