package ru.ideaplatform.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.ideaplatform.identity.Ticket;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketsDto {
    List<Ticket> tickets;
}
