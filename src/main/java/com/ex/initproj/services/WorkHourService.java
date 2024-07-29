package com.ex.initproj.services;

import com.ex.initproj.DTO.CheckInOutput;
import com.ex.initproj.DTO.CheckOutOutput;
import com.ex.initproj.models.User;
import com.ex.initproj.models.WorkHour;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public interface WorkHourService {
    Optional<List<WorkHour>> getAllWorkHourByUsername(User user);
    Optional<CheckInOutput> checkin(User user);
    Optional<CheckOutOutput> checkout(LocalDateTime end, User user);
    Optional<WorkHour> testGetLastWorkHour(User user);
    String deleteWorkHourById(int id);
}
