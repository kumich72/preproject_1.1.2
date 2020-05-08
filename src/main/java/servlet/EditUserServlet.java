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
        name = "EditUserServlet",
        description = "EditUserServlet",
        urlPatterns = {"/admin/edit"}
)
public class EditUserServlet extends HttpServlet {
//    UserService userService = new UserService();

    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("edit_id"));
        User user = UserService.getInstance().getUserById(id);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String message = "";
        try {
            String idUser = req.getParameter("idUser");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            Long id = Long.valueOf(req.getParameter("id"));

            message = UserService.getInstance().editUser(id, name, password, email) ? "edit user successful" : "user did not  edited";
        } catch (DBException e) {
            message = "user did not  edited";
            e.printStackTrace();
        }
        System.out.println(message);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/admin");
    }
}