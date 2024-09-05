package Pegas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    private Long id;
    private String passengerNo;
    private String passengerName;
    private Flight flightId;
    private String seatNo;
    private BigDecimal cost;
}
