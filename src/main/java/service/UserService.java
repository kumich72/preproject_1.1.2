package service;


import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import exception.DBException;
import model.User;
import org.hibernate.SessionFactory;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private SessionFactory sessionFactory;
    private static UserService userService;
    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public User getUserById(Long id) {
        UserHibernateDAO userDAO = new UserHibernateDAO (sessionFactory.openSession());
//        UserJdbcDAO userDAO = getUserDAO();
        try {
            User user = userDAO.getUserById(id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> getAllUsers() {
        UserHibernateDAO userDAO = new UserHibernateDAO (sessionFactory.openSession());
//        UserJdbcDAO userDAO = getUserDAO();
        List<User> users = new ArrayList<>();
        try {
            users = userDAO.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(Long id) throws DBException {
        UserHibernateDAO userDAO = new UserHibernateDAO (sessionFactory.openSession());
//        UserJdbcDAO userDAO = getUserDAO();
        try {
            if(userDAO.deleteUser(id)) {
                return true;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return false;
    }

    public boolean addUser(User user) throws DBException {
        UserHibernateDAO userDAO = new UserHibernateDAO (sessionFactory.openSession());
//        UserJdbcDAO userDAO = getUserDAO();
        try {
            if (userDAO.addUser(user)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public boolean editUser(Long id, String name, String password, String email) throws DBException {
        UserHibernateDAO userDAO = new UserHibernateDAO (sessionFactory.openSession());
//        UserJdbcDAO userDAO = getUserDAO();
        try {
            if (userDAO.editUser(id, name, password, email)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

//
//    public void cleanUp() throws DBException {
//        UserDAO dao = getUserDAO();
//        try {
//            dao.dropTable();
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
//    }

    public void createTable() throws DBException {
        UserHibernateDAO userDAO = new UserHibernateDAO (sessionFactory.openSession());
//        UserJdbcDAO userDAO = getUserDAO();
        try {
            userDAO.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root").       //password
                    append("&serverTimezone=UTC");   //setup server time

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserJdbcDAO getUserDAO() {
        return new UserJdbcDAO(getMysqlConnection());
        }


}