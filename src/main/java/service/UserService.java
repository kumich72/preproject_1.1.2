package service;


import DAO.UserJdbcDAO;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public UserService() {
    }

//    public User getClientById(long id) throws DBException {
//        try {
//            return getUserDAO().getClientById(id);
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
//    }
//
//    public boolean validateClient(String name, String password) {
//        BankClientDAO bankClientDAO = getUserDAO();
//        try {
//            return bankClientDAO.validateClient(name, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public BankClient getClientByName(String name) {
//        UserDAO bankClientDAO = getUserDAO();
//        try {
//            return bankClientDAO.getClientByName(name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public User getUserById(Long id) {
        UserJdbcDAO bankClientDAO = getUserDAO();
        try {
            User user = bankClientDAO.getUserById(id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> getAllUsers() {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        List<User> users = new ArrayList<>();
        try {
            users = userJdbcDAO.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(Long id) throws DBException {
        UserJdbcDAO dao = getUserDAO();
        try {
            if(dao.deleteUser(id)) {
                return true;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return false;
    }

    public boolean addUser(User user) throws DBException {
        UserJdbcDAO dao = getUserDAO();
        try {
            if (dao.addUser(user)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public boolean editUser(Long id, String name, String password, String email) throws DBException {
        UserJdbcDAO dao = getUserDAO();
        try {
            if (dao.editUser(id, name, password, email)) {
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
        UserJdbcDAO dao = getUserDAO();
        try {
            dao.createTable();
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