package com.biblioteca.dao;

import java.sql.SQLException;

public class LivroDAOException extends RuntimeException {
    public LivroDAOException(String message, Throwable cause) {
        super(message, cause);

    }
}
