package Pegas.servlet;

import Pegas.service.FlightService;
import Pegas.service.TicketService;
import Pegas.utils.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {
    private final FlightService flightServlet = FlightService.getINSTANCE();
    private final TicketService ticketService = TicketService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var flights = flightServlet.findAll();
        req.setAttribute("flights", flights);
        req.getRequestDispatcher(JSPHelper.getPath("content")).forward(req,resp);
    }
}
