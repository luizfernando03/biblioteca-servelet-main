package com.biblioteca.servlet;

import com.biblioteca.model.User;
import com.biblioteca.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(name, password, email);
        boolean isRegistered = userService.registerUser(newUser);

        if (isRegistered) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("registerFail.jsp");
        }
    }

}
