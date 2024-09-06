package Pegas.service;

import Pegas.DAO.TicketDao;
import Pegas.DTO.TicketDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private final TicketDao ticketDao = TicketDao.getINSTANCE();

    public List<TicketDTO> findAll(Long id){
        return ticketDao.findAllByFlightId(id).stream().map(i->new TicketDTO(i.getId(),
                i.getFlightId().getId(), i.getSeatNo())).collect(Collectors.toList());
    }

    private TicketService() {};
    private volatile static TicketService INSTANCE;
    public static TicketService getINSTANCE(){
        if(INSTANCE==null){
            synchronized (TicketService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TicketService();
                }
            }
        }
        return INSTANCE;
    }
}
