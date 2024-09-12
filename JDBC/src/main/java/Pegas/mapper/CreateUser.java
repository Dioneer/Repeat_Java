package Pegas.mapper;

import Pegas.DTO.CreateUserDTO;
import Pegas.entity.Gender;
import Pegas.entity.Role;
import Pegas.entity.User;
import Pegas.utils.LocalDateFormatter;

public class CreateUser implements UserMapper<User, CreateUserDTO>{
    private static final CreateUser INSTANCE = new CreateUser();
    @Override
    public User mapFrom(CreateUserDTO from) {
        return User.builder()
                .name(from.getName())
                .birthday(LocalDateFormatter.format(from.getBirthday()))
                .email(from.getEmail())
                .password(from.getPassword())
                .role(Role.valueOf(from.getRole()))
                .gender(Gender.valueOf(from.getGender()))
                .build();
    }
    public static CreateUser getInstance(){
        return INSTANCE;
    }
}
