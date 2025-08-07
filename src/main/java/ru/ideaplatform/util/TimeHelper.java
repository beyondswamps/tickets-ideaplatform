package ru.ideaplatform.util;

import ru.ideaplatform.identity.Ticket;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeHelper {
    public static Duration flightDuration(Ticket ticket) {
        return Duration.between(
                ticket.getDepartureDate().atTime(ticket.getDepartureTime()),
                ticket.getArrivalDate().atTime(ticket.getArrivalTime()));
    }
}
