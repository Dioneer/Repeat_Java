package Pegas.service;

import Pegas.DAO.UserDAO;
import Pegas.DTO.CreateUserDTO;
import Pegas.mapper.CreateUser;

public class UserService {
    private final UserDAO userDAO = UserDAO.getInstance();
    private UserService() {
    }
    private static volatile UserService INSTANCE;

    public static UserService getInstance() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    return INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public Integer create (CreateUserDTO createUserDTO){
        var user = CreateUser.getInstance().mapFrom(createUserDTO);
        var res = userDAO.save(user);
        return res.getId();
    }
}


