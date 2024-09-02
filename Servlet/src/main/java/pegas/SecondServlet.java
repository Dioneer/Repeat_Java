package pegas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var writer = resp.getWriter();
        writer.println(req.getParameter("param1"));
        Map<String, String[]> paramsMap = req.getParameterMap();
        for (Map.Entry<String, String[]> i:paramsMap.entrySet()){
            writer.println(i.getKey()+" : "+ Arrays.toString(i.getValue()));
        }
    }
}
