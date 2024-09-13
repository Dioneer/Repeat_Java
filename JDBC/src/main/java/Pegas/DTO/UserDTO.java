package Pegas.DTO;

import Pegas.entity.Gender;
import Pegas.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDTO {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    Role role;
    Gender gender;

}
