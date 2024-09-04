package Pegas.DAO;

import Pegas.entity.Ticket;
import Pegas.exception.DaoException;
import Pegas.utils.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;

public class TicketDao {
    private static volatile TicketDao INSTANCE;
    private TicketDao(){};

    private final static String sql = """
            insert into ticket(passenger_no, passenger_name, flight_id, seat_no, cost))values (?,?,?,?,?);
            """;
    private final static String sql2 = """
            delete from ticker where id = ?;
            """;

    public TicketDao getINSTANCE(){
        if (INSTANCE==null){
            synchronized (TicketDao.class){
                if (INSTANCE==null){
                    INSTANCE = new TicketDao();
                }
            }
        }
        return INSTANCE;
    }

    public Ticket save(Ticket ticket) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(2, ticket.getPassengerNo());
            statement.setString(3, ticket.getPassengerName());
            statement.setLong(4, ticket.getFlightId());
            statement.setString(5, ticket.getSeatNo());
            statement.setBigDecimal(6, ticket.getCost());
            statement.executeUpdate();
            ResultSet res = statement.getGeneratedKeys();
            if(res.next()){
                ticket.setId(res.getLong("id"));
            }
            return ticket;
        } catch (SQLException | InterruptedException e) {
            throw new DaoException(e);
        }
    }

        public boolean delete(Long id){
            try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(sql2)){
            statement.setLong(1, id);
            return statement.executeUpdate()>0;
            }catch (SQLException | InterruptedException e) {
                throw new DaoException(e);
            }
        }
}
