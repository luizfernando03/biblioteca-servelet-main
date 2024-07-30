package com.biblioteca.servlet;

import com.biblioteca.Service.UserAuthenticator;
import com.biblioteca.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private List<User> users;
    private UserAuthenticator userAuthenticator;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>(); // pode carregar isso de um banco de dados
        userAuthenticator = new UserAuthenticator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User loginAttempt = new User(null, password, email);
        User authenticatedUser = null;
        try {
            authenticatedUser = userAuthenticator.authenticate(loginAttempt, users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (authenticatedUser != null) {
            request.getSession().setAttribute("user", authenticatedUser);
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("loginFail.jsp");
        }
    }
}
