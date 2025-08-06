package ru.ideaplatform;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ideaplatform.identity.Ticket;
import ru.ideaplatform.dto.TicketsDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        File jsonFile = new File(args[0]);
        ObjectMapper mapper = new ObjectMapper();
        TicketsDto tickets;

        try {
            tickets = mapper.readValue(jsonFile, TicketsDto.class);
        } catch (DatabindException de) {
            throw new RuntimeException("Databind processing problem");
        } catch (StreamReadException sre) {
            throw new RuntimeException("Stream processing or parsing problems");
        } catch (IOException e) {
            throw new RuntimeException("Input error");
        }

        List<Ticket> ticketList = tickets.getTickets();
        for (Ticket ticket : ticketList) {
            System.out.println(ticket);
        }
    }
}
