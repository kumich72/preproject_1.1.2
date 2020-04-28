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
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "AddUserServlet",
        description = "AddUserServlet",
        urlPatterns = {"/add"}
        )
    public class AddUserServlet extends HttpServlet {
        UserService userService = new UserService();
        public void init(ServletConfig servletConfig) {
            try {
                super.init(servletConfig);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/add.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> pageVariables = new HashMap<>();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String message = "";
        User user = new User(name, password, email );
        try {
            message = userService.addUser(user) ? "Add user successful" : "user not add";
        } catch (DBException e) {
            message = "user not add";
            e.printStackTrace();
        }
        pageVariables.put("message", message);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/get");
    }
}
