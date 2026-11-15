package br.com.velary.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class ConexaoBanco {
    private static final String URL = "jdbc:sqlite:velary.db";

    private ConexaoBanco() {
    }

    public static Connection abrir() throws SQLException {
        Connection conexao = DriverManager.getConnection(URL);
        try (Statement comando = conexao.createStatement()) {
            comando.execute("PRAGMA foreign_keys = ON");
        }
        return conexao;
    }
}
