package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    User getUserById(Long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    boolean addUser(User user) throws SQLException;

    boolean editUser(Long id, String name, String password, String email) throws SQLException;

    boolean deleteUser(Long id) throws SQLException;
}
