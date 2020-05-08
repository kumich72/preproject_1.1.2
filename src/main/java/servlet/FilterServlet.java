package servlet;


import exception.DBException;
import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//Практическая задача
//Задание:
//1. Добавить классу User поле role типа String, которое будет принимать
// значения «user» или «admin».
//2. На главной странице сделать форму логина, в которую будет вводиться логин и пароль юзера.
//3. Добавить сервлет-фильтр, который после логина будет пропускать пользователей
// с ролью user на страницу /user , а пользователей с ролью admin на страницу /admin
//4. На странице /admin будет таблица всех юзеров с возможностью их изменения
// (она уже сделана ранее). На странице /user можно вывести информацию о
// текущем вошедшем пользователе.
//5. Сделать так, чтобы user не мог заходить на все страницы,
// которые начинаются на /admin (т.e. не мог видеть таблицу пользователей и производить с
// ней какие-либо действия).
//6. Admin может заходить на /user.
//7. Все запросы, связанные с ролью admin должны начинаться с url /admin/.
//
//Ссылки: https://www.codejava.net/java-ee/servlet/webfilter-annotation-examples
@WebFilter("/admin/*")
public class FilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String message = "";
        boolean isAdmin = false;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String name = servletRequest.getParameter("name");
            String password = servletRequest.getParameter("password");
            isAdmin = UserService.getInstance().userIsAdmin(user.getName(),user.getPassword());
        } catch (DBException e) {
            message = "user is not admin; we have error =" + e.getLocalizedMessage();
            e.printStackTrace();
        }
        System.out.println(message);
        if(isAdmin){
            // Разрешить request продвигаться дальше. (Перейти данный Filter).
            filterChain.doFilter(request, response);
        }else{
            response.setStatus(403);
        }
    }
}
