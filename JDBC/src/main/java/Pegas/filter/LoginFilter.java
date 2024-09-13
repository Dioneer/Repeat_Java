package Pegas.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getParameterMap().forEach((k,v)-> System.out.println(k+" : "+ Arrays.toString(v)));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
