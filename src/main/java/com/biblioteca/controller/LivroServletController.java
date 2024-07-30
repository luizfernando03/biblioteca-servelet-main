package com.biblioteca.controller;

import com.biblioteca.Service.LivroService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/Livros")
public class LivroServletController extends HttpServlet {

    private LivroService livroService;

    public void init() {
        livroService = new LivroService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                livroService.excluirLivro(request, response);
                break;
            case "edit":
                livroService.showEditForm(request, response);
                break;
            default:
                livroService.listarLivros(request, response);
                break;
        }
    }

    protected void doPost(@org.jetbrains.annotations.NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                livroService.adicionarLivro(request, response);
                break;
            case "update":
                livroService.atualizarLivro(request, response);
                break;
        }
    }
}
