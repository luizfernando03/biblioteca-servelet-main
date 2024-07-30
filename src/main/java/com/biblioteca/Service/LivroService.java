package com.biblioteca.Service;

import com.biblioteca.model.Livro;
import com.biblioteca.dao.LivroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class LivroService {

    private LivroDAO livroDAO;

    public LivroService() { livroDAO = new LivroDAO(); }

    public void init() {
        livroDAO = new LivroDAO();
    }

    public void listarLivros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Livro> livros = livroDAO.listarLivros();
        request.setAttribute("livros", livros);
        request.getRequestDispatcher("listagem.jsp").forward(request, response);
    }

    public void adicionarLivro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro.setTitulo(titulo);
        livro.setCategoria(categoria);
        livro.setQuantidade(quantidade);

        livroDAO.adicionarLivro(livro);
        response.sendRedirect("LivroServlet");
    }

    public void atualizarLivro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro.setTitulo(titulo);
        livro.setCategoria(categoria);
        livro.setQuantidade(quantidade);

        livroDAO.atualizarLivro(livro);
        response.sendRedirect("LivroServlet");
    }

    public void excluirLivro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isbn = request.getParameter("isbn");
        livroDAO.excluirLivro(isbn);
        response.sendRedirect("LivroServlet");
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        Livro livro = livroDAO.listarLivros().stream().filter(l -> l.getIsbn().equals(isbn)).findFirst().orElse(null);
        request.setAttribute("livro", livro);
        request.getRequestDispatcher("edicao.jsp").forward(request, response);
    }
}
