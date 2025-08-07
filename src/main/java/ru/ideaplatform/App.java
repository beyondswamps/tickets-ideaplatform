package ru.ideaplatform;


import ru.ideaplatform.identity.Ticket;
import ru.ideaplatform.util.FileHelper;
import ru.ideaplatform.util.MathHelper;
import ru.ideaplatform.util.TimeHelper;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class App {
    public static void main(String[] args) {
        List<Ticket> ticketList = FileHelper.ticketListFromJsonFile(args[0]);

        Map<String, Duration> carrierByMinDuration = ticketList.stream()
                .filter(ticket -> ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")
                                        || ticket.getOrigin().equals("TLV") && ticket.getDestination().equals("VVO"))
                .collect(Collectors.toMap(Ticket::getCarrier, TimeHelper::flightDuration,
                        (Duration dur1, Duration dur2) -> (dur1.compareTo(dur2) < 0) ? dur1 : dur2));

        Map<String, String> carrierByMinTime = carrierByMinDuration.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                e -> String.format("%sч%sмин", e.getValue().toHours(), e.getValue().toMinutes() % 60)));


        List<Ticket> ticketsVvoTlv = ticketList.stream()
                .filter(ticket -> ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")
                                  || ticket.getOrigin().equals("TLV") && ticket.getDestination().equals("VVO"))
                .toList();

        Double medianPrice = MathHelper.getMedianTicketPrice(ticketsVvoTlv);
        Double averagePrice = MathHelper.getAverageTicketPrice(ticketsVvoTlv);

        System.out.println(carrierByMinTime);
        System.out.printf("Медианная цена билетов между Владивостоком и Тель-Авивом: %s%n", medianPrice);
        System.out.printf("Средняя цена билетов между Владивостоком и Тель-Авивом: %s%n", averagePrice);

    }
}
