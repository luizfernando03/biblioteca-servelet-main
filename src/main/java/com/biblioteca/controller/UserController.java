package com.biblioteca.controller;

import com.biblioteca.model.User;
import com.biblioteca.Service.UserAuthenticator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/index", "/login", "/addNewUser", "/listAllUsers", "/logout"})
public class UserController extends HttpServlet {
    List<User> users = new ArrayList<>();

    public void init() {
        users.add(new User("Lucas", "123", "lucas@gmail.com"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        UserAuthenticator authenticator = new UserAuthenticator();
        User user;

        switch (action) {
            case "/login": {
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                user = new User(password, email);
                User authUser = null;
                try {
                    authUser = authenticator.authenticate(user, users);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (authUser != null) {
                    request.getSession().setAttribute("user", authUser);
                    request.getRequestDispatcher("welcome.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("user", user);
                    request.getRequestDispatcher("loginFail.jsp").forward(request, response);
                }
            }
            case "/addNewUser": {
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                user = new User(name, password, email);
                if (users.contains(user)) {
                    user = users.get(users.indexOf(user));
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("addFail.jsp").forward(request, response);
                } else {
                    users.add(user);
                    request.getSession().setAttribute("user", user);
                    request.getRequestDispatcher("welcome.jsp").forward(request, response);
                }
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/index":
                request.getRequestDispatcher("index.jsp").forward(request, response);
            case "/listAllUsers": {
                request.setAttribute("users", users);
                request.getRequestDispatcher("listAllUsers.jsp").forward(request, response);
            }
            case "/login":
                request.getRequestDispatcher("login.jsp").forward(request, response);
            case "/logout": {
                request.getSession().invalidate();
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}