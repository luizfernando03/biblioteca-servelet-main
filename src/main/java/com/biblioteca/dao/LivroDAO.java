package com.biblioteca.dao;


import com.biblioteca.Model.Livro;
import com.biblioteca.util.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private final Connection connection;

    public LivroDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO livros (isbn, titulo, categoria, quantidade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getIsbn());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getCategoria());
            stmt.setInt(4, livro.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setIsbn(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setCategoria(rs.getString("categoria"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, categoria = ?, quantidade = ? WHERE isbn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getCategoria());
            stmt.setInt(3, livro.getQuantidade());
            stmt.setString(4, livro.getIsbn());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirLivro(String isbn) {
        String sql = "DELETE FROM livros WHERE isbn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

