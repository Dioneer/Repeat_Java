package Pegas.servlet;

import Pegas.DTO.UserDTO;
import Pegas.service.UserService;
import Pegas.utils.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPHelper.getPath("login")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("email"), req.getParameter("password"))
                .ifPresentOrElse(i-> onLoginSuccess(i, req, resp)
        ,()->onLoginFail(req, resp));
    }

    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("/login?error&email="+req.getParameter("email"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onLoginSuccess(UserDTO i, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", i);
        try {
            resp.sendRedirect("/flights");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
