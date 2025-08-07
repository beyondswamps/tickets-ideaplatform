package ru.ideaplatform.util;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.ideaplatform.dto.TicketsDto;
import ru.ideaplatform.identity.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHelper {
    public static List<Ticket> ticketListFromJsonFile(String jsonFilePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.registerModule(new JavaTimeModule());
        TicketsDto tickets;

        try {
            tickets = mapper.readValue(new File(jsonFilePath), TicketsDto.class);
        } catch (DatabindException de) {
            throw new RuntimeException("DatabindException: " + de.getMessage());
        } catch (StreamReadException sre) {
            throw new RuntimeException("StreamReadException: " + sre.getMessage());
        } catch (IOException ioe) {
            throw new RuntimeException("IOException: " + ioe.getMessage());
        }
        return tickets.getTickets();
    }
}
