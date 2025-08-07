package ru.ideaplatform.util;

import ru.ideaplatform.identity.Ticket;

import java.util.List;
import java.util.stream.Collectors;

import static  java.util.stream.Collectors.averagingDouble;

public class MathHelper {
    public static Double getMedianTicketPrice(List<Ticket> tickets) {
        List<Integer> sortedPriceList = tickets.stream()
                .map(Ticket::getPrice)
                .sorted()
                .toList();
        double median;
        int size = sortedPriceList.size();
        if (size % 2 == 0) {
            median = 0.5 * (sortedPriceList.get(size / 2 - 1) + sortedPriceList.get(size / 2));
        } else {
            median = (double) sortedPriceList.get(size / 2);
        }
        return median;
    }

    public static Double getAverageTicketPrice(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getPrice)
                .collect(averagingDouble(Integer::doubleValue));
    }
}
