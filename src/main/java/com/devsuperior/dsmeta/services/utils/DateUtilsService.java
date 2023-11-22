package com.devsuperior.dsmeta.services.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtilsService {

    public static LocalDate stringToLocalDate(String dataString) {

        return LocalDate.parse(dataString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
