package trabalhoHelpDesk;

import javax.swing.*;
import java.util.ArrayList;

public class SistemaHelpDesk {
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Tecnico> tecnicos;
    private ArrayList<Chamado> chamados;

    public SistemaHelpDesk() {
        funcionarios = new ArrayList<>();
        tecnicos = new ArrayList<>();
        chamados = new ArrayList<>();
    }

    // Cadastro de Funcionário com validação manual
    public void cadastrarFuncionario() {
        String nome = JOptionPane.showInputDialog("Digite o nome do Funcionário:");
        String email = JOptionPane.showInputDialog("Digite o email do Funcionário:");
        String setor = JOptionPane.showInputDialog("Digite o setor do Funcionário:");

        if (nome == null || nome.isEmpty() || email == null || email.isEmpty() || setor == null || setor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Todos os campos devem ser preenchidos!");
            return;
        }

        if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            JOptionPane.showMessageDialog(null, "Erro: O nome não pode conter números!");
            return;
        }

        Funcionario funcionario = new Funcionario(nome, email, setor);
        funcionarios.add(funcionario);
        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
    }

    public void cadastrarTecnico() {
        String nome = JOptionPane.showInputDialog("Digite o nome do Técnico:");
        String especialidade = JOptionPane.showInputDialog("Digite a especialidade do Técnico:");

        if (nome == null || nome.isEmpty() || especialidade == null || especialidade.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Todos os campos devem ser preenchidos!");
            return;
        }

        if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            JOptionPane.showMessageDialog(null, "Erro: O nome não pode conter números!");
            return;
        }

        Tecnico tecnico = new Tecnico(nome, especialidade);
        tecnicos.add(tecnico);
        JOptionPane.showMessageDialog(null, "Técnico cadastrado com sucesso!");
    }

    
    // Abertura de Chamado com validação manual
    public void abrirChamado() {
        String titulo = JOptionPane.showInputDialog("Digite o título do chamado:");
        String descricao = JOptionPane.showInputDialog("Digite a descrição do chamado:");

        if (titulo == null || titulo.isEmpty() || descricao == null || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Título e descrição do chamado devem ser preenchidos!");
            return;
        }

        Funcionario funcionario = selecionarFuncionario();
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum funcionário selecionado!");
            return;
        }

        Chamado chamado = new Chamado(titulo, descricao, funcionario);
        chamados.add(chamado);
        JOptionPane.showMessageDialog(null, "Chamado aberto com sucesso!");
    }

    // Atribuir Técnico ao Chamado com validação manual
    public void atribuirTecnicoChamado() {
        Chamado chamado = selecionarChamado();
        if (chamado == null) {
            JOptionPane.showMessageDialog(null, "Erro: Chamado não encontrado!");
            return;
        }

        Tecnico tecnico = selecionarTecnico();
        if (tecnico == null) {
            JOptionPane.showMessageDialog(null, "Erro: Técnico não encontrado!");
            return;
        }

        chamado.atribuirTecnico(tecnico);
        JOptionPane.showMessageDialog(null, "Técnico atribuído ao chamado!");
    }

    // Atualizar Status do Chamado com validação manual
    public void atualizarStatusChamado() {
        Chamado chamado = selecionarChamado();
        if (chamado == null) {
            JOptionPane.showMessageDialog(null, "Erro: Chamado não encontrado!");
            return;
        }

        String novoStatus = JOptionPane.showInputDialog("Digite o novo status (Aberto, Em atendimento, Resolvido):");

        if (novoStatus == null || novoStatus.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O status não pode ser vazio!");
            return;
        }

        chamado.setStatus(novoStatus);
        JOptionPane.showMessageDialog(null, "Status do chamado atualizado para " + novoStatus);
    }

    // Selecionar Funcionário sem método genérico
    private Funcionario selecionarFuncionario() {
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum funcionário cadastrado!");
            return null;
        }

        StringBuilder sb = new StringBuilder("Selecione o funcionário:\n");
        for (int i = 0; i < funcionarios.size(); i++) {
            sb.append(i + 1).append(" - ").append(funcionarios.get(i).getNome()).append("\n");
        }

        return obterSelecao(sb.toString(), funcionarios);
    }

    // Selecionar Técnico sem método genérico
    private Tecnico selecionarTecnico() {
        if (tecnicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum técnico cadastrado!");
            return null;
        }

        StringBuilder sb = new StringBuilder("Selecione o técnico:\n");
        for (int i = 0; i < tecnicos.size(); i++) {
            sb.append(i + 1).append(" - ").append(tecnicos.get(i).getNome()).append("\n");
        }

        return obterSelecao(sb.toString(), tecnicos);
    }

    // Selecionar Chamado sem método genérico
    private Chamado selecionarChamado() {
        if (chamados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum chamado cadastrado!");
            return null;
        }

        StringBuilder sb = new StringBuilder("Selecione o chamado:\n");
        for (int i = 0; i < chamados.size(); i++) {
            sb.append(i + 1).append(" - ").append(chamados.get(i).getTitulo()).append("\n");
        }

        return obterSelecao(sb.toString(), chamados);
    }

    // Método para obtenção de seleção sem uso de um método genérico separado
    private <T> T obterSelecao(String mensagem, ArrayList<T> lista) {
        String escolha = JOptionPane.showInputDialog(mensagem);
        if (escolha == null || escolha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Entrada inválida!");
            return null;
        }

        try {
            int index = Integer.parseInt(escolha) - 1;
            if (index < 0 || index >= lista.size()) {
                JOptionPane.showMessageDialog(null, "Erro: Opção inválida!");
                return null;
            }
            return lista.get(index);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Entrada deve ser um número válido!");
            return null;
        }
    }

    // Método principal
    public static void main(String[] args) {
        SistemaHelpDesk sistema = new SistemaHelpDesk();
        sistema.menu();
    }

    // Menu de opções
    public void menu() {
        while (true) {
            String opcoes = """
                1. Cadastrar Funcionário
                2. Cadastrar Técnico
                3. Abrir Chamado
                4. Atribuir Técnico ao Chamado
                5. Atualizar Status do Chamado
                6. Sair
            """;
            String escolha = JOptionPane.showInputDialog(opcoes);

            switch (escolha) {
                case "1" -> cadastrarFuncionario();
                case "2" -> cadastrarTecnico();
                case "3" -> abrirChamado();
                case "4" -> atribuirTecnicoChamado();
                case "5" -> atualizarStatusChamado();
                case "6" -> System.exit(0);
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}