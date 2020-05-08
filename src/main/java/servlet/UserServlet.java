package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "UserServlet",
        description = "UserServlet",
        urlPatterns = {"/user"}
)
public class UserServlet extends HttpServlet {
    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = UserService.getInstance().getUserByNameAndPassword(name, password);
        req.setAttribute("user", user);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("name", user.getName());
        session.setAttribute("password", user.getPassword());
        getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}