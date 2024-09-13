package Pegas.DAO;

import Pegas.entity.Gender;
import Pegas.entity.Role;
import Pegas.entity.User;
import Pegas.utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User>{
    private static final String SAVE_SQL = "INSERT INTO users (name,birthday,email,password,role,gender) " +
            "values (?,?,?,?,?,?);";
    private static final String GET_BY_EMAIL_PASS = "select * from users where email=? and password =?;";

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

    public Optional<User> findByEmailAndPass(String email, String pass){
        try(var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(GET_BY_EMAIL_PASS)){
            statement.setString(1, email);
            statement.setString(2, pass);

            var result = statement.executeQuery();
            User user = null;
            while (result.next()){
                user = buildEntity(result);
            }
            return Optional.ofNullable(user);
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildEntity(ResultSet result) throws SQLException {
            return User.builder()
                    .id(result.getInt("id"))
                    .name(result.getString("name"))
                    .password(result.getString("password"))
                    .birthday(result.getObject("birthday", LocalDate.class))
                    .role(Role.valueOf(result.getString("role")))
                    .gender(Gender.valueOf(result.getString("gender")))
                    .email(result.getString("email"))
                    .build();
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
