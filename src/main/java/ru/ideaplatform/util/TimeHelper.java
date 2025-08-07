package ru.ideaplatform.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeHelper {
    public static Duration flightDuration(LocalDate departureDate,
                                          LocalTime departureTime,
                                          LocalDate arrivalDate,
                                          LocalTime arrivalTime) {
        return Duration.between(departureDate.atTime(departureTime), arrivalDate.atTime(arrivalTime));
    }
}
