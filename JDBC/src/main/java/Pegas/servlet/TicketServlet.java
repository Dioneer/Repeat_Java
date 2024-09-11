package Pegas.servlet;

import Pegas.service.TicketService;
import Pegas.utils.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        res.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try(var writer = res.getWriter()){
            writer.write("<html><body>");
            writer.write("<h1>List of Tickets for flights:</h1>");
            writer.write("<ul>");
            Long flightId = Long.valueOf(req.getParameter("flightId"));
            req.setAttribute("tickets", ticketService.findAll(flightId));
            req.getRequestDispatcher(JSPHelper.getPath("tickets")).forward(req, res);
        }
    }
}
