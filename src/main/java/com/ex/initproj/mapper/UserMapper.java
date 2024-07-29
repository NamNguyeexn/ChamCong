package com.ex.initproj.mapper;

import com.ex.initproj.DTO.HumanInput;
import com.ex.initproj.DTO.UserInput;
import com.ex.initproj.DTO.UserOutput;
import com.ex.initproj.models.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "idHuman", target = "humanid")
    @Mapping(source = "humanInput.username", target = "username")
    @Mapping(source = "humanInput.password", target = "password")
    @Mapping(source = "employeeCode", target = "employeeCode")
    @Mapping(source = "role", target = "role")
    User inputRegisterToUser(HumanInput humanInput, int idHuman, String role, String employeeCode);
}
