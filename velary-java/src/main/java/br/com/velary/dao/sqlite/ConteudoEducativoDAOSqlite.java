package br.com.velary.dao.sqlite;

import br.com.velary.config.ConexaoBanco;
import br.com.velary.dao.ConteudoEducativoDAO;
import br.com.velary.model.ConteudoEducativo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConteudoEducativoDAOSqlite implements ConteudoEducativoDAO {
    private static final String SELECT_COM_FASE = """
            SELECT c.id, c.fase_id, f.nome AS fase_nome, c.titulo, c.resumo, c.tipo, c.link
            FROM conteudo_educativo c
            INNER JOIN fase_saude f ON f.id = c.fase_id
            """;

    @Override
    public void criar(ConteudoEducativo conteudo) throws SQLException {
        String sql = "INSERT INTO conteudo_educativo (fase_id, titulo, resumo, tipo, link) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preencher(comando, conteudo);
            comando.executeUpdate();
            try (ResultSet chaves = comando.getGeneratedKeys()) {
                if (chaves.next()) {
                    conteudo.setId(chaves.getLong(1));
                }
            }
        }
    }

    @Override
    public List<ConteudoEducativo> listarTodos() throws SQLException {
        List<ConteudoEducativo> conteudos = new ArrayList<>();
        String sql = SELECT_COM_FASE + " ORDER BY f.nome, c.titulo";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                conteudos.add(mapear(resultado));
            }
        }
        return conteudos;
    }

    @Override
    public Optional<ConteudoEducativo> buscarPorId(long id) throws SQLException {
        String sql = SELECT_COM_FASE + " WHERE c.id = ?";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setLong(1, id);
            try (ResultSet resultado = comando.executeQuery()) {
                return resultado.next() ? Optional.of(mapear(resultado)) : Optional.empty();
            }
        }
    }

    @Override
    public boolean atualizar(ConteudoEducativo conteudo) throws SQLException {
        String sql = "UPDATE conteudo_educativo SET fase_id = ?, titulo = ?, resumo = ?, tipo = ?, link = ? WHERE id = ?";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            preencher(comando, conteudo);
            comando.setLong(6, conteudo.getId());
            return comando.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(long id) throws SQLException {
        String sql = "DELETE FROM conteudo_educativo WHERE id = ?";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setLong(1, id);
            return comando.executeUpdate() > 0;
        }
    }

    private void preencher(PreparedStatement comando, ConteudoEducativo conteudo) throws SQLException {
        comando.setLong(1, conteudo.getFaseId());
        comando.setString(2, conteudo.getTitulo());
        comando.setString(3, conteudo.getResumo());
        comando.setString(4, conteudo.getTipo());
        comando.setString(5, conteudo.getLink());
    }

    private ConteudoEducativo mapear(ResultSet resultado) throws SQLException {
        return new ConteudoEducativo(
                resultado.getLong("id"),
                resultado.getLong("fase_id"),
                resultado.getString("fase_nome"),
                resultado.getString("titulo"),
                resultado.getString("resumo"),
                resultado.getString("tipo"),
                resultado.getString("link"));
    }
}
