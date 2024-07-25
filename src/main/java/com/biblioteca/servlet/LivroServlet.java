package com.biblioteca.servlet;

import com.biblioteca.Service.LivroService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LivroServlet extends HttpServlet {

    private LivroService livroService;

    @Override
    public void init() throws ServletException {
        super.init(); // Chama o init() da classe pai para garantir a inicialização padrão do servlet
        livroService = new LivroService();
        livroService.init(); // Inicializa o LivroService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            livroService.listarLivros(request, response);
            request.getRequestDispatcher("/WEB-INF/jsp/livros.jsp").forward(request, response); // Certifique-se de que o caminho está correto
        } else if (action.equals("edit")) {
            livroService.showEditForm(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            livroService.adicionarLivro(request, response);
        } else if (action.equals("update")) {
            livroService.atualizarLivro(request, response);
        } else if (action.equals("delete")) {
            livroService.excluirLivro(request, response);
        }
    }
}
