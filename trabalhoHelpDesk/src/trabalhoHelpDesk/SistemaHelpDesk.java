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

    // Cadastro de Funcionário com validação
    public void cadastrarFuncionario1() {
        String nome = JOptionPane.showInputDialog("Digite o nome do Funcionário:");
        String email = JOptionPane.showInputDialog("Digite o email do Funcionário:");
        String setor = JOptionPane.showInputDialog("Digite o setor do Funcionário:");

        if (nome == null || nome.isEmpty() || email == null || email.isEmpty() || setor == null || setor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Todos os campos devem ser preenchidos!");
            return;
        }

        Funcionario funcionario = new Funcionario(nome, email, setor);
        funcionarios.add(funcionario);
        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
    }

    // Cadastro de Técnico com validação
    public void cadastrarTecnico1() {
        String nome = JOptionPane.showInputDialog("Digite o nome do Técnico:");
        String especialidade = JOptionPane.showInputDialog("Digite a especialidade do Técnico:");

        if (nome == null || nome.isEmpty() || especialidade == null || especialidade.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Todos os campos devem ser preenchidos!");
            return;
        }

        Tecnico tecnico = new Tecnico(nome, especialidade);
        tecnicos.add(tecnico);
        JOptionPane.showMessageDialog(null, "Técnico cadastrado com sucesso!");
    }

    // Abertura de Chamado com validação
    public void abrirChamado1() {
        String titulo = JOptionPane.showInputDialog("Digite o título do chamado:");
        String descricao = JOptionPane.showInputDialog("Digite a descrição do chamado:");
        Funcionario funcionario = selecionarFuncionario();

        if (titulo == null || titulo.isEmpty() || descricao == null || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Título e descrição do chamado devem ser preenchidos!");
            return;
        }

        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum funcionário selecionado!");
            return;
        }

        Chamado chamado = new Chamado(titulo, descricao, funcionario);
        chamados.add(chamado);
        JOptionPane.showMessageDialog(null, "Chamado aberto com sucesso!");
    }

    // Atribuir Técnico ao Chamado com validação
    public void atribuirTecnicoChamado1() {
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

    // Atualizar Status do Chamado com validação
    public void atualizarStatusChamado1() {
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

    // Selecionar Funcionário
    private Funcionario selecionarFuncionario() {
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum funcionário cadastrado!");
            return null;
        }

        return validarSelecao("Selecione o funcionário:", funcionarios);
    }

    // Selecionar Chamado
    private Chamado selecionarChamado() {
        if (chamados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum chamado cadastrado!");
            return null;
        }

        return validarSelecao("Selecione o chamado:", chamados);
    }

    // Selecionar Técnico
    private Tecnico selecionarTecnico() {
        if (tecnicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum técnico cadastrado!");
            return null;
        }

        return validarSelecao("Selecione o técnico:", tecnicos);
    }

    // Método genérico para validar seleção
    private <T> T validarSelecao(String mensagem, ArrayList<T> lista) {
        StringBuilder sb = new StringBuilder(mensagem).append("\n");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(i + 1).append(" - ").append(lista.get(i)).append("\n");
        }

        String escolha = JOptionPane.showInputDialog(sb.toString());

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

    // Listar Chamados por Status
    public void listarChamadosPorStatus() {
        String status = JOptionPane.showInputDialog("Digite o status para filtrar (Aberto, Em atendimento, Resolvido):");

        if (status == null || status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O status não pode ser vazio!");
            return;
        }

        StringBuilder sb = new StringBuilder("Chamados com status '").append(status).append("':\n");
        boolean encontrou = false;

        for (Chamado chamado : chamados) {
            if (chamado.getStatus().equalsIgnoreCase(status)) {
                sb.append(chamado.getTitulo()).append(" - ").append(chamado.getDescricao()).append("\n");
                encontrou = true;
            }
        }

        if (!encontrou) {
            sb.append("Nenhum chamado encontrado com esse status.");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Menu principal
    public void menu() {
        while (true) {
            String opcoes = """
                1. Cadastrar Funcionário
                2. Cadastrar Técnico
                3. Abrir Chamado
                4. Atribuir Técnico ao Chamado
                5. Listar Chamados por Status
                6. Atualizar Status do Chamado
                7. Sair
            """;
            String escolha = JOptionPane.showInputDialog(opcoes);

            switch (escolha) {
                case "1" -> cadastrarFuncionario1();
                case "2" -> cadastrarTecnico1();
                case "3" -> abrirChamado1();
                case "4" -> atribuirTecnicoChamado1();
                case "5" -> listarChamadosPorStatus();
                case "6" -> atualizarStatusChamado1();
                case "7" -> System.exit(0);
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    public static void main(String[] args) {
        SistemaHelpDesk sistema = new SistemaHelpDesk();
        sistema.menu();
    }
}