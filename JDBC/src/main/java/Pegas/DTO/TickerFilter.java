package Pegas.DTO;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class TickerFilter {
    private final String passengerName;
    private final String seatNo;
    private final int limit;
    private final int offset;
}
