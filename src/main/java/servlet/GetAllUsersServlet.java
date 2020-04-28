package servlet;

import exception.DBException;
import model.User;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "GetAllUsersServlet",
        description = "GetAllUsersServlet",
        urlPatterns = {"/"}
)
public class GetAllUsersServlet extends HttpServlet {

    UserService userService = new UserService();
    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        users = userService.getAllUsers();
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/getAll.jsp").forward(req, resp);
    }
}
