package ru.ideaplatform.Identity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    String origin;
    String originName;
    String destination;
    String destinationName;
    LocalDate departureDate;
    LocalTime departureTime;
    LocalDate arrivalDate;
    LocalTime arrivalTime;
    String carrier;
    Integer stops;
    Integer price;

}
