package br.com.velary.dao;

import br.com.velary.model.ConteudoEducativo;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ConteudoEducativoDAO {
    void criar(ConteudoEducativo conteudo) throws SQLException;
    List<ConteudoEducativo> listarTodos() throws SQLException;
    Optional<ConteudoEducativo> buscarPorId(long id) throws SQLException;
    boolean atualizar(ConteudoEducativo conteudo) throws SQLException;
    boolean excluir(long id) throws SQLException;
}
