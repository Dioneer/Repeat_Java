package pegas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setHeader("My_header", "hi");
        var writer = resp.getWriter();
        writer.println("<h1>Hi first servlet<h1>");
        String str = req.getHeader("user-agent");
        writer.println(str);
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()){
            String s = headers.nextElement();
            writer.println("<h3>"+s+":"+req.getHeader(s)+"</h3>");
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var writer = resp.getWriter();
        writer.println("<h1>"+"login: "+req.getParameter("login")+"<h1>");
        Cookie[] qrr = req.getCookies();
        for (Cookie i: qrr){
            writer.println(i.getValue());
        }
        writer.println("<h2>"+"Pass: "+req.getParameter("pass")+"<h2>");
    }

    @Override
    public void destroy() {
    }
}
