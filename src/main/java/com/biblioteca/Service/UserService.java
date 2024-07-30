package com.biblioteca.Service;

import com.biblioteca.model.User;
import com.biblioteca.dao.UserDAO;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    /**
     * Autentica um usuário verificando suas credenciais.
     * @param email O email do usuário.
     * @param password A senha do usuário.
     * @return O usuário autenticado, ou null se as credenciais estiverem incorretas.
     */
    public User authenticateUser(String email, String password) {
        List<User> userList = userDAO.getAllUsers();
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Recupera todos os usuários.
     * @return Uma lista de todos os usuários.
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Registra um novo usuário.
     * @param user O usuário a ser registrado.
     * @return true se o usuário foi registrado com sucesso, false se o email já está em uso.
     */
    public boolean registerUser(User user) {
        List<User> userList = userDAO.getAllUsers();
        for (User existingUser : userList) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                return false; // Usuário já existe
            }
        }
        userDAO.addUser(user);
        return true;
    }


}
