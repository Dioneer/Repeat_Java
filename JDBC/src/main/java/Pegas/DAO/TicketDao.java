package Pegas.DAO;

import Pegas.entity.Ticket;
import Pegas.exception.DaoException;
import Pegas.utils.ConnectionManager;

import java.sql.*;

public class TicketDao {
    private static volatile TicketDao INSTANCE = new TicketDao();
    private TicketDao(){};

    private final static String sql = """
            insert into test.ticket(passenger_no, passenger_name, flight_id, seat_no, cost)values (?,?,?,?,?);
            """;
    private final static String sql2 = """
            delete from test.ticket where id = ?;
            """;

    public static TicketDao getINSTANCE(){
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
            statement.setString(1, ticket.getPassengerNo());
            statement.setString(2, ticket.getPassengerName());
            statement.setLong(3, ticket.getFlightId());
            statement.setString(4, ticket.getSeatNo());
            statement.setBigDecimal(5, ticket.getCost());
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
