package ru.ideaplatform;

import ru.ideaplatform.identity.Ticket;
import ru.ideaplatform.util.FileHelper;
import ru.ideaplatform.util.MathHelper;
import ru.ideaplatform.util.TimeHelper;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Ticket> ticketList = FileHelper.ticketListFromJsonFile(args[0]);
        List<Ticket> ticketsVvoTlv = filterByAirportCode(ticketList, "VVO", "TLV");

        Map<String, Duration> carrierByMinDuration = ticketsVvoTlv.stream()
                .collect(Collectors.toMap(Ticket::getCarrier, TimeHelper::flightDuration,
                        (Duration dur1, Duration dur2) -> (dur1.compareTo(dur2) < 0) ? dur1 : dur2));

        StringBuilder minFlightVvoTlvText = new StringBuilder(
                "Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика:\n");
        for (Map.Entry<String, Duration> e : carrierByMinDuration.entrySet()) {
            minFlightVvoTlvText.append(String.format("%s: %s часов %s минут%n",
                    e.getKey(),
                    e.getValue().toHours(),
                    e.getValue().toMinutes() % 60));
        }

        BigDecimal medianPrice = MathHelper.getMedianTicketPrice(ticketsVvoTlv);
        BigDecimal averagePrice = MathHelper.getAverageTicketPrice(ticketsVvoTlv);

        System.out.println(minFlightVvoTlvText);
        System.out.printf(
                "Разница между средней ценой и медианой между городами Владивосток и Тель-Авив: %s%n",
                medianPrice.subtract(averagePrice).abs());
    }

    public static List<Ticket> filterByAirportCode(List<Ticket> tickets, String airportCode1, String airportCode2) {
        return tickets.stream()
                .filter(ticket -> ticket.getOrigin().equals(airportCode1) && ticket.getDestination().equals(airportCode2)
                                  || ticket.getOrigin().equals(airportCode2) && ticket.getDestination().equals(airportCode1))
                .toList();
    }
}
