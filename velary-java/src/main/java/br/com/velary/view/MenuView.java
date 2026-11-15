package br.com.velary.view;

import br.com.velary.controller.ConteudoEducativoController;
import br.com.velary.controller.FaseSaudeController;
import br.com.velary.model.ConteudoEducativo;
import br.com.velary.model.FaseSaude;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuView {
    private final Scanner entrada;
    private final FaseSaudeController faseController;
    private final ConteudoEducativoController conteudoController;

    public MenuView(Scanner entrada, FaseSaudeController faseController,
                    ConteudoEducativoController conteudoController) {
        this.entrada = entrada;
        this.faseController = faseController;
        this.conteudoController = conteudoController;
    }

    public void executar() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== VELARY | CATÁLOGO EDUCATIVO ===");
            System.out.println("1 - Gerenciar fases de saúde");
            System.out.println("2 - Gerenciar conteúdos educativos");
            System.out.println("0 - Sair");
            int opcao = lerInteiro("Escolha: ");

            try {
                switch (opcao) {
                    case 1 -> menuFases();
                    case 2 -> menuConteudos();
                    case 0 -> continuar = false;
                    default -> System.out.println("Escolha uma opção do menu.");
                }
            } catch (IllegalArgumentException erro) {
                System.out.println("Atenção: " + erro.getMessage());
            } catch (SQLException erro) {
                mostrarErroBanco(erro);
            }
        }
        System.out.println("Até logo!");
    }

    private void menuFases() throws SQLException {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- FASES DE SAÚDE ---");
            System.out.println("1 - Cadastrar fase");
            System.out.println("2 - Listar fases");
            System.out.println("3 - Atualizar fase");
            System.out.println("4 - Excluir fase");
            System.out.println("0 - Voltar");
            int opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarFase();
                case 2 -> listarFases();
                case 3 -> atualizarFase();
                case 4 -> excluirFase();
                case 0 -> voltar = true;
                default -> System.out.println("Escolha uma opção do menu.");
            }
        }
    }

    private void menuConteudos() throws SQLException {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- CONTEÚDOS EDUCATIVOS ---");
            System.out.println("1 - Cadastrar conteúdo");
            System.out.println("2 - Listar conteúdos");
            System.out.println("3 - Atualizar conteúdo");
            System.out.println("4 - Excluir conteúdo");
            System.out.println("0 - Voltar");
            int opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarConteudo();
                case 2 -> listarConteudos();
                case 3 -> atualizarConteudo();
                case 4 -> excluirConteudo();
                case 0 -> voltar = true;
                default -> System.out.println("Escolha uma opção do menu.");
            }
        }
    }

    private void cadastrarFase() throws SQLException {
        FaseSaude fase = faseController.criar(lerTexto("Nome da fase: "), lerTexto("Descrição: "));
        System.out.println("Fase cadastrada com código " + fase.getId() + ".");
    }

    private void listarFases() throws SQLException {
        List<FaseSaude> fases = faseController.listar();
        if (fases.isEmpty()) {
            System.out.println("Nenhuma fase cadastrada.");
            return;
        }
        fases.forEach(fase -> System.out.printf("%d | %s — %s%n",
                fase.getId(), fase.getNome(), fase.getDescricao()));
    }

    private void atualizarFase() throws SQLException {
        long id = lerLong("Código da fase: ");
        Optional<FaseSaude> fase = faseController.buscar(id);
        if (fase.isEmpty()) {
            System.out.println("Fase não encontrada.");
            return;
        }
        boolean atualizada = faseController.atualizar(id,
                lerTexto("Novo nome: "), lerTexto("Nova descrição: "));
        System.out.println(atualizada ? "Fase atualizada." : "Nenhuma alteração foi feita.");
    }

    private void excluirFase() throws SQLException {
        long id = lerLong("Código da fase que deseja excluir: ");
        boolean excluida = faseController.excluir(id);
        System.out.println(excluida ? "Fase excluída." : "Fase não encontrada.");
    }

    private void cadastrarConteudo() throws SQLException {
        if (!mostrarFasesParaEscolha()) {
            System.out.println("Cadastre uma fase antes de incluir conteúdos.");
            return;
        }
        long faseId = lerLong("Código da fase relacionada: ");
        ConteudoEducativo conteudo = conteudoController.criar(
                faseId,
                lerTexto("Título: "),
                lerTexto("Resumo: "),
                lerTexto("Tipo (artigo, guia, vídeo ou página): "),
                lerTexto("Link (opcional): "));
        System.out.println("Conteúdo cadastrado com código " + conteudo.getId() + ".");
    }

    private void listarConteudos() throws SQLException {
        List<ConteudoEducativo> conteudos = conteudoController.listar();
        if (conteudos.isEmpty()) {
            System.out.println("Nenhum conteúdo cadastrado.");
            return;
        }
        conteudos.forEach(this::imprimirConteudo);
    }

    private void atualizarConteudo() throws SQLException {
        long id = lerLong("Código do conteúdo: ");
        Optional<ConteudoEducativo> conteudo = conteudoController.buscar(id);
        if (conteudo.isEmpty()) {
            System.out.println("Conteúdo não encontrado.");
            return;
        }
        mostrarFasesParaEscolha();
        long faseId = lerLong("Novo código da fase relacionada: ");
        boolean atualizado = conteudoController.atualizar(
                id,
                faseId,
                lerTexto("Novo título: "),
                lerTexto("Novo resumo: "),
                lerTexto("Novo tipo: "),
                lerTexto("Novo link (opcional): "));
        System.out.println(atualizado ? "Conteúdo atualizado." : "Nenhuma alteração foi feita.");
    }

    private void excluirConteudo() throws SQLException {
        long id = lerLong("Código do conteúdo que deseja excluir: ");
        boolean excluido = conteudoController.excluir(id);
        System.out.println(excluido ? "Conteúdo excluído." : "Conteúdo não encontrado.");
    }

    private boolean mostrarFasesParaEscolha() throws SQLException {
        List<FaseSaude> fases = faseController.listar();
        if (fases.isEmpty()) {
            return false;
        }
        System.out.println("Fases disponíveis:");
        fases.forEach(fase -> System.out.printf("%d - %s%n", fase.getId(), fase.getNome()));
        return true;
    }

    private void imprimirConteudo(ConteudoEducativo conteudo) {
        System.out.printf("%d | %s (%s)%n", conteudo.getId(), conteudo.getTitulo(), conteudo.getTipo());
        System.out.printf("   Fase: %s [código %d]%n", conteudo.getFaseNome(), conteudo.getFaseId());
        System.out.println("   Resumo: " + conteudo.getResumo());
        if (conteudo.getLink() != null && !conteudo.getLink().isBlank()) {
            System.out.println("   Link: " + conteudo.getLink());
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine();
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem).trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException erro) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }

    private long lerLong(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem).trim();
            try {
                return Long.parseLong(texto);
            } catch (NumberFormatException erro) {
                System.out.println("Digite o código usando apenas números.");
            }
        }
    }

    private void mostrarErroBanco(SQLException erro) {
        String mensagem = erro.getMessage() == null ? "" : erro.getMessage();
        if (mensagem.contains("FOREIGN KEY constraint failed")) {
            System.out.println("Não dá para excluir essa fase: primeiro exclua os conteúdos ligados a ela.");
        } else if (mensagem.contains("UNIQUE constraint failed")) {
            System.out.println("Já existe uma fase com esse nome. Escolha outro nome.");
        } else {
            System.out.println("Erro ao acessar o banco: " + mensagem);
        }
    }
}
