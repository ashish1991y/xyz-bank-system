package com.xyzbank.eob.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@Slf4j
public class DateConversionService {
    /**
     * Converts a string formatted as "dd/MM/yyyy" to a LocalDate.
     * @param dateString the string to convert
     * @return the corresponding LocalDate, or null if the input is null or invalid
     */
    public static LocalDate convertStringToLocalDate(String dateString) {
        if (dateString == null) {
            log.warn("Attempted to convert a null date string to LocalDate");
            return null;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            log.error("Error parsing date string: {}, with exception: {}", dateString, e.getMessage());
            return null;  // Consider rethrowing as a custom checked exception if the API requires it
        }
    }
}

