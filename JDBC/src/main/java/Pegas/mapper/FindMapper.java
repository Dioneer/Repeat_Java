package Pegas.mapper;

import Pegas.DTO.UserDTO;
import Pegas.entity.User;

public class FindMapper implements UserMapper<UserDTO, User>{
    private static final FindMapper INSTANCE = new FindMapper();
    public static FindMapper getInstance(){
        return INSTANCE;
    }
    private FindMapper(){};
    @Override
    public UserDTO mapFrom(User from) {
        return UserDTO.builder()
                .name(from.getName())
                .id(from.getId())
                .role(from.getRole())
                .birthday(from.getBirthday())
                .email(from.getEmail())
                .build();
    }
}
