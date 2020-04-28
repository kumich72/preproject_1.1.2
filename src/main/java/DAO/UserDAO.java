package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users WHERE id='" + id + "'");
        ResultSet result = statement.getResultSet();
        if (result.next()) {
            User user = new User();
            user.setId(result.getLong("id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            return user;
        }
        result.close();
        statement.close();
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<User>();

        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users");
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }
        return users;
    }

    public boolean addUser(User user) throws SQLException {
        if (getUserIdByName(user.getName()) == 0) {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate("INSERT INTO users (name, password, email) VALUES ('"
                    + user.getName() + "', '" + user.getPassword() + "' ,'" + user.getEmail() + "')");
            statement.close();
            return true;
        }
        return false;
    }

    public boolean editUser(Long id, String name, String password, String email) throws SQLException {
        if (userExistById(id)) {
            String SQLQuery = "UPDATE users SET email = ?, password = ?  WHERE  id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        }
        return false;
    }

    private boolean userExistById(Long id) throws SQLException {
        boolean isExist = false;
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM users WHERE id='" + id + "'");
        ResultSet result = stmt.getResultSet();
        if (result.next()) {
            isExist = true;
        }
        result.close();
        stmt.close();
        return isExist;
    }

    public void deleteUser(Long id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM users WHERE id = '" + id + "'");
        stmt.close();
    }

    public long getUserIdByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("select * from users where name='" + name + "'");
        ResultSet result = stmt.getResultSet();
        Long id = Long.valueOf(0);
        if (result.next()) {
            id = result.getLong("id");
            result.close();
            stmt.close();
        }
        return id;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), password varchar(256), email varchar(256), primary key (id))");
        stmt.close();
    }



//    public void dropTable() throws SQLException {
//        Statement stmt = connection.createStatement();
//        stmt.executeUpdate("DROP TABLE IF EXISTS users");
//        stmt.close();
//    }


}
