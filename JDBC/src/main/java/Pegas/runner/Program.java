package Pegas.runner;

import Pegas.DAO.TicketDao;
import Pegas.entity.Ticket;

import java.math.BigDecimal;

public class Program {
    public static void main(String[] args) {
        TicketDao ticketDao = TicketDao.getINSTANCE();
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
    }
}
