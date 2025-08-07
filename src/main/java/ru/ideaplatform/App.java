package ru.ideaplatform;


import ru.ideaplatform.identity.Ticket;
import ru.ideaplatform.util.FileHelper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.min;
import static java.util.stream.Collectors.minBy;


public class App {
    public static void main(String[] args) {
        List<Ticket> ticketList = FileHelper.ticketListFromJsonFile(args[0]);

//       Map<String, Optional<Integer>> map = ticketList.stream()
//                .filter(ticket -> ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")
//                || ticket.getOrigin().equals("TLV") && ticket.getDestination().equals("VVO"))
//                .collect(Collectors.groupingBy(Ticket::getCarrier, Collectors.mapping(Ticket::getPrice, minBy(Integer::compareTo))));
//
        Map<String, Integer> map = ticketList.stream()
                .filter(ticket -> ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")
                || ticket.getOrigin().equals("TLV") && ticket.getDestination().equals("VVO"))
                .collect(Collectors.toMap(Ticket::getCarrier, Ticket::getPrice, Math::min));

//        Integer minPriceVvoTlv = ticketList.stream()
//                .filter(ticket -> (ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV"))
//                              || (ticket.getOrigin().equals("TLV") && ticket.getDestination().equals("VVO")))
//                .map(Ticket::getPrice)
//                .min(Integer::compareTo)
//                .orElse(0);

        System.out.println(map);

    }
}
