package Pegas.DTO;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class TicketDTO {
    private final Long id;
    private final Long flightId;
    private final String seatNo;
}
