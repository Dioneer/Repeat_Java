package pegas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="hellServ", value = "/hello-serv")
public class Main extends HttpServlet {
    private String message;

    public void init(){message = "Hello, Lena";}
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
               res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>"+message+"<h1>");
        out.println("<body><html>");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
