package Pegas.DAO;

import Pegas.DTO.TickerFilter;
import Pegas.entity.Flight;
import Pegas.entity.FlightStatus;
import Pegas.entity.Ticket;
import Pegas.exception.DaoException;
import Pegas.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketDao implements DAO<Ticket>{
    private static volatile TicketDao INSTANCE;
    private static FlightDao flightDao = FlightDao.getINSTANCE();
    private TicketDao(){};

    private final static String save = """
            insert into test.ticket(passenger_no, passenger_name, flight_id, seat_no, cost)values (?,?,?,?,?);
            """;
    private final static String del = """
            delete from test.ticket where id = ?;
            """;
    private final static String findAll = """
            select * from test.ticket t
            join test.flight f on t.flight_id=f.id           
            """;
    private final static String findById = """
            select * from test.ticket t
            where t.id = ?;
            """;
    private final static String update = """
            update test.ticket t set passenger_name = ?
            where t.id = ?;
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
             PreparedStatement statement = connection.prepareStatement(save, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, ticket.getPassengerNo());
            statement.setString(2, ticket.getPassengerName());
            statement.setLong(3, ticket.getFlightId().getId());
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
            PreparedStatement statement = connection.prepareStatement(del)){
            statement.setLong(1, id);
            return statement.executeUpdate()>0;
            }catch (SQLException | InterruptedException e) {
                throw new DaoException(e);
            }
        }

    public List<Ticket> findAll(){
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findAll)){
            List<Ticket> arr = new ArrayList<>();
            var res = statement.executeQuery();
            while(res.next()){
                arr.add(createTicket(res));
            }
            return arr;
        }catch (SQLException | InterruptedException e) {
            throw new DaoException(e);
        }
    }

    public List<Ticket> findAll(TickerFilter filter){
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if(filter.getPassengerName() != null){
            parameters.add(filter.getPassengerName());
            whereSql.add("passenger_name = ?");
        }
        if(filter.getSeatNo() != null){
            parameters.add("%"+filter.getSeatNo()+"%");
            whereSql.add("seat_no like ?");
        }
        parameters.add(filter.getLimit());
        parameters.add(filter.getOffset());
        String where = whereSql.stream().collect(Collectors.joining(" and ", " where ", " limit ? " +
                "offset ?"));
        String findFilter = findAll + where;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findFilter)){
            List<Ticket> arr = new ArrayList<>();
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i+1,parameters.get(i));
            }
            var res = statement.executeQuery();
            while(res.next()){
                arr.add(createTicket(res));
            }
            return arr;
        }catch (SQLException | InterruptedException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Ticket> findById(Long id){
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findById)){
            Ticket ticket = null;
            statement.setLong(1, id);
            var res = statement.executeQuery();
            while (res.next()){
                ticket = createTicket(res);
            }
            return Optional.ofNullable(ticket);
        }catch (SQLException | InterruptedException e) {
            throw new DaoException(e);
        }
    }

    public boolean update(Long id, String name){
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(update)){
            statement.setString(1, name);
            statement.setLong(2, id);
            return statement.executeUpdate()>0;
        }catch (SQLException | InterruptedException e) {
            throw new DaoException(e);
        }
    }

    private Ticket createTicket(ResultSet res) throws SQLException {
        var flight = new Flight(res.getLong("id"),res.getString("flight_no"),
                res.getTimestamp("departure_date").toLocalDateTime(),
                res.getString("departure_airport_code"),
                res.getTimestamp("arrival_date").toLocalDateTime(),
                res.getString("arrival_airoport_code"),res.getInt("aircraft_id"),
                FlightStatus.valueOf(res.getString("status")));
        return new Ticket(res.getLong("id"),res.getString("passenger_no"),
                res.getString("passenger_name"),
                flightDao.findById(res.getLong("flight_id")).orElse(null),
                res.getString("seat_no"),
                res.getBigDecimal("cost"));
    }
}
