package com.ex.initproj.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ConvertData {
    public static String convertEmployeeCode(String role, String input){
        return role + StringUtils.leftPad(input.trim(), 8, "0");
    }
}
