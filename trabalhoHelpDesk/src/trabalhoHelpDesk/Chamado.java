package trabalhoHelpDesk;

import java.util.Date;

public class Chamado {
    private String titulo;
    private String descricao;
    private Funcionario funcionarioSolicitante;
    private Date dataAbertura;
    private String status;
    private Tecnico tecnicoResponsavel;

    public Chamado(String titulo, String descricao, Funcionario funcionarioSolicitante) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.funcionarioSolicitante = funcionarioSolicitante;
        this.dataAbertura = new Date();
        this.status = "Aberto";
        this.tecnicoResponsavel = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Funcionario getFuncionarioSolicitante() {
        return funcionarioSolicitante;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public String getStatus() {
        return status;
    }

    public Tecnico getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void atribuirTecnico(Tecnico tecnico) {
        this.tecnicoResponsavel = tecnico;
    }
}