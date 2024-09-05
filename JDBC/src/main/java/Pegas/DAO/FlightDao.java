package Pegas.DAO;

import Pegas.entity.Flight;
import Pegas.entity.FlightStatus;
import Pegas.entity.Ticket;
import Pegas.exception.DaoException;
import Pegas.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements DAO<Flight>{
    private final String del = """
            delete from test.flight f where f.id=?;
            """;
    private final String save = """
            insert into test.flight(flight_no,departure_date,departure_airport_code,arrival_date,
            arrival_airoport_code,aircraft_id,status) values(?,?,?,?,?,?,?);
            """;
    private final String findAll = """
            select * from test.flight;
            """;
    private final String findById = """
            select * from test.flight f
            where f.id = ?;
            """;

    public List<Flight> findAll(){
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findAll)){
            List<Flight> arr = new ArrayList<>();
            var res = statement.executeQuery();
            while(res.next()){
                arr.add(createFlight(res));
            }
            return arr;
        }catch (SQLException | InterruptedException e) {
            throw new DaoException(e);
        }
    }

    private Flight createFlight(ResultSet res) throws SQLException {
        return new Flight(res.getLong("id"),res.getString("flight_no"),
                res.getTimestamp("departure_date").toLocalDateTime(),
                res.getString("departure_airport_code"),
                res.getTimestamp("arrival_date").toLocalDateTime(),
                res.getString("arrival_airoport_code"),res.getInt("aircraft_id"),
                FlightStatus.valueOf(res.getString("status")));
    }

    @Override
    public boolean delete(Long id) {
        try (var connection= ConnectionManager.get(); var statement = connection.prepareStatement(del)){
            statement.setLong(1,id);
            return statement.executeUpdate()>0;
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flight save(Flight ticket) {
        try (var connection= ConnectionManager.get(); var statement = connection.prepareStatement(save, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,ticket.getFlightNo());
            statement.setTimestamp(2, Timestamp.valueOf(ticket.getDepartureDate()));
            statement.setString(3, ticket.getDepartureAirportCode());
            statement.setTimestamp(4, Timestamp.valueOf(ticket.getArrivalDate()));
            statement.setString(5, ticket.getArrivalAiroportCode());
            statement.setInt(6, ticket.getAircraftId());
            statement.setString(7, String.valueOf(ticket.getStatus()));
            statement.executeUpdate();
            var res = statement.getGeneratedKeys();
            while (res.next()){
                ticket.setId(res.getLong("id"));
            }
            return ticket;
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        try (var connection = ConnectionManager.get(); var statement = connection.prepareStatement(findById)) {
            statement.setLong(1, id);
            var res = statement.executeQuery();
            Flight flight = null;
            while (res.next()) {
                flight=createFlight(res);
            }
            return Optional.ofNullable(flight);
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public boolean update(Long id, String name) {
        return false;
    }
    private static volatile FlightDao INSTANCE;
    private FlightDao(){};
    public static FlightDao getINSTANCE(){
        if(INSTANCE==null){
            synchronized (FlightDao.class){
                if(INSTANCE==null){
                    return INSTANCE = new FlightDao();
                }
            }
        }
        return INSTANCE;
    }
}
