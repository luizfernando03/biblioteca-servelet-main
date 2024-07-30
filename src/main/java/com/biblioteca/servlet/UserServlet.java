package com.biblioteca.servlet;

import com.biblioteca.model.User;
import com.biblioteca.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            listarUsuarios(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            autenticarUsuario(request, response);
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.getAllUsers();
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/WEB-INF/jsp/listAllUsers.jsp").forward(request, response);
    }

    private void autenticarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

//        User user = new User(email, password);
        User authenticatedUser = userService.authenticateUser(email, password);

        if (authenticatedUser != null) {
            request.getSession().setAttribute("user", authenticatedUser);
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("loginFail.jsp");
        }
    }
}
