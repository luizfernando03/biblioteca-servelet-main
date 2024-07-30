package com.biblioteca.dao;

import com.biblioteca.model.User;
import com.biblioteca.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Recupera todos os usuários do banco de dados.
     * @return Uma lista de usuários.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user
                        .setName(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("senha"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Adiciona um novo usuário ao banco de dados.
     * @param user O usuário a ser adicionado.
     */
    public void addUser(User user) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza as informações de um usuário no banco de dados.
     * @param user O usuário com as informações atualizadas.
     */
    public void updateUser(User user) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove um usuário do banco de dados.
     * @param userId O ID do usuário a ser removido.
     */
    public void deleteUser(int userId) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera um usuário específico pelo ID.
     * @param userId O ID do usuário a ser recuperado.
     * @return O usuário com o ID especificado.
     */
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("nome"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("senha")); // Supondo que a senha está armazenada no banco de dados
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
