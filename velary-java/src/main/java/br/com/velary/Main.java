package br.com.velary;

import br.com.velary.config.InicializadorBanco;
import br.com.velary.controller.ConteudoEducativoController;
import br.com.velary.controller.FaseSaudeController;
import br.com.velary.dao.ConteudoEducativoDAO;
import br.com.velary.dao.FaseSaudeDAO;
import br.com.velary.dao.sqlite.ConteudoEducativoDAOSqlite;
import br.com.velary.dao.sqlite.FaseSaudeDAOSqlite;
import br.com.velary.view.MenuView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            InicializadorBanco.inicializar();

            FaseSaudeDAO faseDAO = new FaseSaudeDAOSqlite();
            ConteudoEducativoDAO conteudoDAO = new ConteudoEducativoDAOSqlite();
            FaseSaudeController faseController = new FaseSaudeController(faseDAO);
            ConteudoEducativoController conteudoController =
                    new ConteudoEducativoController(conteudoDAO, faseDAO);

            try (Scanner entrada = new Scanner(System.in)) {
                new MenuView(entrada, faseController, conteudoController).executar();
            }
        } catch (SQLException | IOException erro) {
            System.err.println("Não foi possível iniciar o Velary: " + erro.getMessage());
        }
    }
}
