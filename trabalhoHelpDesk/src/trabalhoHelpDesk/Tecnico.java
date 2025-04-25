package trabalhoHelpDesk;

public class Tecnico {
    private String nome;
    private String especialidade;

    public Tecnico(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}