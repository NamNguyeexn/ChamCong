package com.ex.initproj.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerateWorkHourCode {
    public static String generateWorkHourCode(LocalDateTime start, int userid) {
        return start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + userid;
    }
}
