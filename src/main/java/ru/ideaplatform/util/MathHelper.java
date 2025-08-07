package ru.ideaplatform.util;

import ru.ideaplatform.identity.Ticket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MathHelper {
    public static BigDecimal getMedianTicketPrice(List<Ticket> tickets) {
        List<BigDecimal> sortedPriceList = tickets.stream()
                .map(Ticket::getPrice)
                .sorted()
                .toList();
        BigDecimal median;
        int size = sortedPriceList.size();
        if (size % 2 == 0) {
            median = (sortedPriceList.get(size / 2 - 1))
                    .add(sortedPriceList.get(size / 2))
                    .divide(new BigDecimal(2), RoundingMode.HALF_UP);
        } else {
            median = sortedPriceList.get(size / 2);
        }
        return median;
    }

    public static BigDecimal getAverageTicketPrice(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(tickets.size()), RoundingMode.HALF_UP);
    }
}
