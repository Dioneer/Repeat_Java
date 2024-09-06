package Pegas.runner;

import Pegas.DAO.FlightDao;
import Pegas.DAO.TicketDao;
import Pegas.DTO.TickerFilter;
import Pegas.entity.Flight;
import Pegas.entity.FlightStatus;
import Pegas.entity.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args) {
        TicketDao ticketDao = TicketDao.getINSTANCE();
        FlightDao flightDao = FlightDao.getINSTANCE();
        /**
         * save
         */
//        Ticket ticket = new Ticket();
//        ticket.setPassengerNo("123sa");
//        ticket.setPassengerName("Elena");
//        ticket.setFlightId(1L);
//        ticket.setSeatNo("3e");
//        ticket.setCost(BigDecimal.TEN);
//        System.out.println(ticketDao.save(ticket));
        /**
         * delete
         */
//        System.out.println(ticketDao.delete(2L));
        /**
         * findAll
         */
//        System.out.println(ticketDao.findAll());
        /**
         * find by id
         */
//        System.out.println(ticketDao.findById(1L).orElseThrow());
        /**
         * update
         */
//        System.out.println(ticketDao.update(3L,"Mikle"));
        /**
         * findFilter
         */
//        TickerFilter tickerFilter = new TickerFilter("asdasd","1q",1,0);
//        System.out.println(ticketDao.findAll(tickerFilter));
        /**
         * flight delete
         */
//        System.out.println(flightDao.delete(1L));
        /**
         * flight save
         */
//        var flight = new Flight();
//        flight.setFlightNo("asd1");
//        flight.setDepartureDate(LocalDateTime.now());
//        flight.setDepartureAirportCode("QWE");
//        flight.setArrivalDate(LocalDateTime.now());
//        flight.setAircraftId(2);
//        flight.setStatus(FlightStatus.ARRIVED);
//        System.out.println(flightDao.save(flight));
        /**
         * find all
         */
//        System.out.println(flightDao.findAll());
        /**
         * find by id
         */
//        System.out.println(flightDao.findById(1L));
        System.out.println(ticketDao.findAllByFlightId(1L));
    }
}
