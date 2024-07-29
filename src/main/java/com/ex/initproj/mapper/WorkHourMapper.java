package com.ex.initproj.mapper;

import com.ex.initproj.DTO.CheckInOutput;
import com.ex.initproj.DTO.CheckOutOutput;
import com.ex.initproj.models.User;
import com.ex.initproj.models.WorkHour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
//@Component
public interface WorkHourMapper {
    WorkHourMapper INSTANCE = Mappers.getMapper(WorkHourMapper.class);
    @Mapping(source = "workHour.start", target = "start")
    @Mapping(source = "user.employeeCode", target = "employeeCode")
    @Mapping(source = "workHour.status", target = "status")
    CheckInOutput workHourToCheckInOutPut(WorkHour workHour, User user);
    @Mapping(source = "workHour.start", target = "start")
    @Mapping(source = "workHour.end", target = "end")
    @Mapping(source = "user.employeeCode", target = "employeeCode")
    @Mapping(source = "workHour.status", target = "status")
    @Mapping(source = "workHour.note", target = "note")
    CheckOutOutput workHourToCheckOutOutput(WorkHour workHour, User user);
}
