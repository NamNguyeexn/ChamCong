package com.ex.initproj.mapper;

import com.ex.initproj.DTO.HumanInput;
import com.ex.initproj.DTO.HumanOutput;
import com.ex.initproj.models.Human;
import com.ex.initproj.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface HumanMapper {
    HumanMapper INSTANCE = Mappers.getMapper(HumanMapper.class);
    @Mapping(source = "user.employeeCode", target = "employeeCode")
    @Mapping(source = "humanInput.name", target = "name")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "password", target = "password")
    HumanOutput userToHumanOutput(User user, HumanInput humanInput, String password);
    @Mapping(source = "humanInput.name", target = "name")
    @Mapping(source = "dob", target = "dob")
    @Mapping(source = "humanInput.address", target = "address")
    @Mapping(source = "humanInput.phone", target = "phone")
    Human humanInputToHuman(HumanInput humanInput, Date dob);
}
