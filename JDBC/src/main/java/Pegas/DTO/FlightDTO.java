package Pegas.DTO;

import lombok.AllArgsConstructor;
import lombok.Value;


@AllArgsConstructor
@Value
public class FlightDTO {
    private final Long id;
    private final String description;

}
