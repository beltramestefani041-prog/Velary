package br.com.velary.controller;

import br.com.velary.dao.FaseSaudeDAO;
import br.com.velary.model.FaseSaude;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FaseSaudeController {
    private final FaseSaudeDAO faseDAO;

    public FaseSaudeController(FaseSaudeDAO faseDAO) {
        this.faseDAO = faseDAO;
    }

    public FaseSaude criar(String nome, String descricao) throws SQLException {
        FaseSaude fase = new FaseSaude(0, validar(nome, "nome", 80), validar(descricao, "descrição", 500));
        faseDAO.criar(fase);
        return fase;
    }

    public List<FaseSaude> listar() throws SQLException {
        return faseDAO.listarTodos();
    }

    public Optional<FaseSaude> buscar(long id) throws SQLException {
        validarId(id);
        return faseDAO.buscarPorId(id);
    }

    public boolean atualizar(long id, String nome, String descricao) throws SQLException {
        validarId(id);
        FaseSaude fase = new FaseSaude(id, validar(nome, "nome", 80), validar(descricao, "descrição", 500));
        return faseDAO.atualizar(fase);
    }

    public boolean excluir(long id) throws SQLException {
        validarId(id);
        return faseDAO.excluir(id);
    }

    private String validar(String valor, String campo, int limite) {
        String texto = valor == null ? "" : valor.trim();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Informe " + campo + ".");
        }
        if (texto.length() > limite) {
            throw new IllegalArgumentException("O campo " + campo + " aceita até " + limite + " caracteres.");
        }
        return texto;
    }

    private void validarId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O código precisa ser maior que zero.");
        }
    }
}
