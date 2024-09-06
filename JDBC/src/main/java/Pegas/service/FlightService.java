package Pegas.service;

import Pegas.DAO.FlightDao;
import Pegas.DTO.FlightDTO;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDao flightDao = FlightDao.getINSTANCE();

    public List<FlightDTO> findAll(){
        return flightDao.findAll().stream().map(flight -> new FlightDTO(flight.getId(),
                "%s - %s - %s".formatted(flight.getArrivalAiroportCode(),flight.getDepartureAirportCode(),
                flight.getStatus()))).collect(Collectors.toList());
    }

    private FlightService(){};
    private volatile static FlightService INSTANCE;
    public static FlightService getINSTANCE(){
        if(INSTANCE==null){
            synchronized (FlightService.class){
                if(INSTANCE==null){
                    return INSTANCE=new FlightService();
                }
            }
        }
        return INSTANCE;
    }
}
