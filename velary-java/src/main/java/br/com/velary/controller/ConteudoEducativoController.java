package br.com.velary.controller;

import br.com.velary.dao.ConteudoEducativoDAO;
import br.com.velary.dao.FaseSaudeDAO;
import br.com.velary.model.ConteudoEducativo;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConteudoEducativoController {
    private final ConteudoEducativoDAO conteudoDAO;
    private final FaseSaudeDAO faseDAO;

    public ConteudoEducativoController(ConteudoEducativoDAO conteudoDAO, FaseSaudeDAO faseDAO) {
        this.conteudoDAO = conteudoDAO;
        this.faseDAO = faseDAO;
    }

    public ConteudoEducativo criar(long faseId, String titulo, String resumo, String tipo, String link)
            throws SQLException {
        validarFase(faseId);
        ConteudoEducativo conteudo = new ConteudoEducativo(
                0, faseId, null,
                validar(titulo, "título", 120),
                validar(resumo, "resumo", 1000),
                validar(tipo, "tipo", 40),
                validarOpcional(link, "link", 300));
        conteudoDAO.criar(conteudo);
        return conteudo;
    }

    public List<ConteudoEducativo> listar() throws SQLException {
        return conteudoDAO.listarTodos();
    }

    public Optional<ConteudoEducativo> buscar(long id) throws SQLException {
        validarId(id);
        return conteudoDAO.buscarPorId(id);
    }

    public boolean atualizar(long id, long faseId, String titulo, String resumo, String tipo, String link)
            throws SQLException {
        validarId(id);
        validarFase(faseId);
        ConteudoEducativo conteudo = new ConteudoEducativo(
                id, faseId, null,
                validar(titulo, "título", 120),
                validar(resumo, "resumo", 1000),
                validar(tipo, "tipo", 40),
                validarOpcional(link, "link", 300));
        return conteudoDAO.atualizar(conteudo);
    }

    public boolean excluir(long id) throws SQLException {
        validarId(id);
        return conteudoDAO.excluir(id);
    }

    private void validarFase(long faseId) throws SQLException {
        validarId(faseId);
        if (faseDAO.buscarPorId(faseId).isEmpty()) {
            throw new IllegalArgumentException("A fase informada não existe. Cadastre ou escolha uma fase válida.");
        }
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

    private String validarOpcional(String valor, String campo, int limite) {
        String texto = valor == null ? "" : valor.trim();
        if (texto.length() > limite) {
            throw new IllegalArgumentException("O campo " + campo + " aceita até " + limite + " caracteres.");
        }
        return texto.isEmpty() ? null : texto;
    }

    private void validarId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O código precisa ser maior que zero.");
        }
    }
}
