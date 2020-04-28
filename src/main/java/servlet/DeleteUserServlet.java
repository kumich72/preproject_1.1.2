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
        name = "DeleteUserServlet",
        description = "DeleteUserServlet",
        urlPatterns = {"/delete"}
        )
public class DeleteUserServlet extends HttpServlet {
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
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String message ="";

        try {
            String idText = req.getParameter("id");
            Long id = Long.valueOf(idText);
            message = userService.deleteUser(id) ? "Delete user successful" : "user not deleted";
        } catch (DBException e) {
            message = "user not deleted";
            e.printStackTrace();
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/get");
    }
}
