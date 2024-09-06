package Pegas.servlet;

import Pegas.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getINSTANCE();


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        res.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try(var writer = res.getWriter()){
            writer.write("<html><body>");
            writer.write("<h1>List of flights:</h1>");
            writer.write("<ul>");
            flightService.findAll().stream().forEach(i->writer.write("""
                    <li>
                    <a href='/Servlet/tickets?flightId=%d'>%s</a>
                    </li>
                    """.formatted(i.getId(),i.getDescription())));
            writer.write("</ul>");
            writer.write("</body></html>");
        }
    }
}
