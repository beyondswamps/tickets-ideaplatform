package ru.ideaplatform.identity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    String origin;
    String originName;
    String destination;
    String destinationName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    LocalDate departureDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    LocalTime departureTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    LocalDate arrivalDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    LocalTime arrivalTime;
    String carrier;
    Integer stops;
    BigDecimal price;
}
