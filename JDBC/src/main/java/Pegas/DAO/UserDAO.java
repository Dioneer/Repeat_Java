package Pegas.DAO;

import Pegas.entity.User;
import Pegas.utils.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User>{
    private static final String SAVE_SQL = "INSERT INTO users (name,birthday,email,password,role,gender) " +
            "values (?,?,?,?,?,?);";


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public User save(User user) {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1,user.getName());
            statement.setObject(2,user.getBirthday());
            statement.setObject(3,user.getEmail());
            statement.setObject(4,user.getPassword());
            statement.setObject(5,user.getRole().name());
            statement.setObject(6,user.getGender().name());

            statement.executeQuery();

            ResultSet res = statement.getGeneratedKeys();
            while (res.next()){
                user.setId(res.getObject("id", Integer.class));
            }
            return user;
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean update(Long id, String name) {
        return false;
    }
    private UserDAO(){};
    private static volatile UserDAO INSTANCE;
    public static UserDAO getInstance(){
        if(INSTANCE==null){
            synchronized(UserDAO.class){
                if(INSTANCE==null){
                    return INSTANCE=new UserDAO();
                }
            }
        }
        return INSTANCE;
    }
}
