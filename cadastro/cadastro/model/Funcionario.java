package model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private String formacao;
    private int tempoExperiencia;
    private List<Integer> tiposAtendidos; // Tipos que o funcionário pode atender
    private StatusFuncionario status;
    private List<Servico> servicosAtivos;

    public Funcionario(String nome, String formacao, int tempoExperiencia) {
        this.id = proximoId++;
        this.nome = nome;
        this.formacao = formacao;
        this.tempoExperiencia = tempoExperiencia;
        this.tiposAtendidos = new ArrayList<>();
        this.status = StatusFuncionario.ATIVO;
        this.servicosAtivos = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }
    
    public int getTempoExperiencia() { return tempoExperiencia; }
    public void setTempoExperiencia(int tempoExperiencia) { this.tempoExperiencia = tempoExperiencia; }
    
    public List<Integer> getTiposAtendidos() { return new ArrayList<>(tiposAtendidos); }
    public void setTiposAtendidos(List<Integer> tipos) { this.tiposAtendidos = new ArrayList<>(tipos); }
    
    public StatusFuncionario getStatus() { return status; }
    public void setStatus(StatusFuncionario status) { this.status = status; }
    
    public List<Servico> getServicosAtivos() { return new ArrayList<>(servicosAtivos); }

    // Métodos específicos
    public void adicionarTipoAtendido(int tipo) {
        if (!tiposAtendidos.contains(tipo)) {
            tiposAtendidos.add(tipo);
        }
    }

    public boolean podeAtenderTipo(int tipo) {
        return status == StatusFuncionario.ATIVO && tiposAtendidos.contains(tipo);
    }

    public boolean isDisponivel() {
        return status == StatusFuncionario.ATIVO;
    }

    public void adicionarServicoAtivo(Servico servico) {
        if (!servicosAtivos.contains(servico)) {
            servicosAtivos.add(servico);
        }
    }

    public void removerServicoAtivo(Servico servico) {
        servicosAtivos.remove(servico);
    }

    public int getQuantidadeServicosAtivos() {
        return servicosAtivos.size();
    }

    @Override
    public String toString() {
        return String.format("%d - %s (%s, %d anos de experiência) - Status: %s - Serviços ativos: %d",
                id, nome, formacao, tempoExperiencia, status, servicosAtivos.size());
    }
}