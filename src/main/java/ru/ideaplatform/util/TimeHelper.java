package ru.ideaplatform.util;

import ru.ideaplatform.identity.Ticket;

import java.time.Duration;

public class TimeHelper {
    public static Duration flightDuration(Ticket ticket) {
        return Duration.between(
                ticket.getDepartureDate().atTime(ticket.getDepartureTime()),
                ticket.getArrivalDate().atTime(ticket.getArrivalTime()));
    }
}
