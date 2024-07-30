package com.biblioteca.Service;

import com.biblioteca.model.User;

import java.sql.*;
import java.util.List;

public class UserAuthenticator {
    public User authenticate(User user, List<User> users) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            return user;
        }

        return null;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projeto1", "username", "password");
    }
}
