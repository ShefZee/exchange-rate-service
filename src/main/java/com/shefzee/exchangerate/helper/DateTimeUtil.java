package com.shefzee.exchangerate.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public static LocalDate convertStringToLocalDate(String date){
        //convert String to LocalDate
        return LocalDate.parse(date, formatter);
    }
}
