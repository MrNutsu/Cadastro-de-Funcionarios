package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Servico {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private String descricao;
    private String cliente;
    private String cep;
    private int tipoArea;
    private Funcionario funcionario;
    private LocalDateTime dataCriacao;
    private StatusServico status;

    public enum StatusServico {
        ATIVO, CONCLUIDO, CANCELADO
    }

    public Servico(String nome, String descricao, String cliente, String cep, int tipoArea, Funcionario funcionario) {
        this.id = proximoId++;
        this.nome = nome;
        this.descricao = descricao;
        this.cliente = cliente;
        this.cep = cep;
        this.tipoArea = tipoArea;
        this.funcionario = funcionario;
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusServico.ATIVO;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    
    public int getTipoArea() { return tipoArea; }
    public void setTipoArea(int tipoArea) { this.tipoArea = tipoArea; }
    
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public StatusServico getStatus() { return status; }
    public void setStatus(StatusServico status) { this.status = status; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("""
                ID: %d
                Serviço: %s
                Descrição: %s
                Cliente: %s
                CEP: %s
                Área: Tipo %d
                Funcionário: %s
                Data: %s
                Status: %s
                """, id, nome, descricao, cliente, cep, tipoArea, 
                funcionario.getNome(), dataCriacao.format(formatter), status);
    }
}