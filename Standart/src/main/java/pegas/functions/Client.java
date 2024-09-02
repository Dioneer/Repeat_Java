package pegas.functions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {
    private String name;
    private int balance;
    private boolean isActive;
}
