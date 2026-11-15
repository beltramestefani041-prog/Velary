package br.com.velary.dao;

import br.com.velary.model.FaseSaude;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FaseSaudeDAO {
    void criar(FaseSaude fase) throws SQLException;
    List<FaseSaude> listarTodos() throws SQLException;
    Optional<FaseSaude> buscarPorId(long id) throws SQLException;
    boolean atualizar(FaseSaude fase) throws SQLException;
    boolean excluir(long id) throws SQLException;
}
