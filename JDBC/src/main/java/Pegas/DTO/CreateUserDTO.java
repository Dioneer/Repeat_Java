package Pegas.DTO;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDTO {
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
}
