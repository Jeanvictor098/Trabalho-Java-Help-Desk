package trabalhoHelpDesk;

public class Funcionario {
    private String nome;
    private String email;
    private String setor;

    public Funcionario(String nome, String email, String setor) {
        this.nome = nome;
        this.email = email;
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSetor() {
        return setor;
    }
}