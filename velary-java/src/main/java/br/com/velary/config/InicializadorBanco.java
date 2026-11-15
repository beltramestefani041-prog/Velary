package br.com.velary.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public final class InicializadorBanco {
    private InicializadorBanco() {
    }

    public static void inicializar() throws SQLException, IOException {
        InputStream recurso = InicializadorBanco.class.getResourceAsStream("/schema.sql");
        if (recurso == null) {
            throw new IOException("O arquivo schema.sql não foi encontrado em src/main/resources.");
        }

        String script;
        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(recurso, StandardCharsets.UTF_8))) {
            script = leitor.lines().collect(Collectors.joining("\n"));
        }

        try (Connection conexao = ConexaoBanco.abrir(); Statement comando = conexao.createStatement()) {
            for (String instrucao : script.split(";")) {
                if (!instrucao.isBlank()) {
                    comando.execute(instrucao.trim());
                }
            }
        }
    }
}
