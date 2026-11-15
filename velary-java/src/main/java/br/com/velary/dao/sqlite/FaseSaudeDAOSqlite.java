package br.com.velary.dao.sqlite;

import br.com.velary.config.ConexaoBanco;
import br.com.velary.dao.FaseSaudeDAO;
import br.com.velary.model.FaseSaude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FaseSaudeDAOSqlite implements FaseSaudeDAO {
    @Override
    public void criar(FaseSaude fase) throws SQLException {
        String sql = "INSERT INTO fase_saude (nome, descricao) VALUES (?, ?)";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            comando.setString(1, fase.getNome());
            comando.setString(2, fase.getDescricao());
            comando.executeUpdate();
            try (ResultSet chaves = comando.getGeneratedKeys()) {
                if (chaves.next()) {
                    fase.setId(chaves.getLong(1));
                }
            }
        }
    }

    @Override
    public List<FaseSaude> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, descricao FROM fase_saude ORDER BY nome";
        List<FaseSaude> fases = new ArrayList<>();
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                fases.add(mapear(resultado));
            }
        }
        return fases;
    }

    @Override
    public Optional<FaseSaude> buscarPorId(long id) throws SQLException {
        String sql = "SELECT id, nome, descricao FROM fase_saude WHERE id = ?";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setLong(1, id);
            try (ResultSet resultado = comando.executeQuery()) {
                return resultado.next() ? Optional.of(mapear(resultado)) : Optional.empty();
            }
        }
    }

    @Override
    public boolean atualizar(FaseSaude fase) throws SQLException {
        String sql = "UPDATE fase_saude SET nome = ?, descricao = ? WHERE id = ?";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, fase.getNome());
            comando.setString(2, fase.getDescricao());
            comando.setLong(3, fase.getId());
            return comando.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(long id) throws SQLException {
        String sql = "DELETE FROM fase_saude WHERE id = ?";
        try (Connection conexao = ConexaoBanco.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setLong(1, id);
            return comando.executeUpdate() > 0;
        }
    }

    private FaseSaude mapear(ResultSet resultado) throws SQLException {
        return new FaseSaude(
                resultado.getLong("id"),
                resultado.getString("nome"),
                resultado.getString("descricao"));
    }
}
