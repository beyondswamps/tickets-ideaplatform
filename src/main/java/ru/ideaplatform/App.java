package ru.ideaplatform;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.ideaplatform.identity.Ticket;
import ru.ideaplatform.dto.TicketsDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        File jsonFile = new File(args[0]);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.registerModule(new JavaTimeModule());
        TicketsDto tickets;

        try {
            tickets = mapper.readValue(jsonFile, TicketsDto.class);
        } catch (DatabindException de) {
            throw new RuntimeException("DatabindException: " + de.getMessage());
        } catch (StreamReadException sre) {
            throw new RuntimeException("StreamReadException: " + sre.getMessage());
        } catch (IOException ioe) {
            throw new RuntimeException("IOException: " + ioe.getMessage());
        }

        List<Ticket> ticketList = tickets.getTickets();
        for (Ticket ticket : ticketList) {
            System.out.println(ticket);
        }
    }
}
