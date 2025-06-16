package service;

import model.*;
import java.util.*;
import java.util.stream.Collectors;

public class SistemaService {
    private Map<Integer, AreaEspecialidade> areas;
    private Map<Integer, Funcionario> funcionarios;
    private Map<Integer, Servico> servicos;

    public SistemaService() {
        this.areas = new HashMap<>();
        this.funcionarios = new HashMap<>();
        this.servicos = new HashMap<>();
    }

    // === ÁREAS DE ESPECIALIDADE ===
    public void adicionarArea(AreaEspecialidade area) {
        areas.put(area.getTipo(), area);
    }

    public List<AreaEspecialidade> listarAreas() {
        return new ArrayList<>(areas.values());
    }

    public AreaEspecialidade buscarAreaPorTipo(int tipo) {
        return areas.get(tipo);
    }

    public boolean existeArea(int tipo) {
        return areas.containsKey(tipo);
    }

    // === FUNCIONÁRIOS ===
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.put(funcionario.getId(), funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios.values());
    }

    public List<Funcionario> buscarFuncionariosDisponiveis(int tipoArea) {
        return funcionarios.values().stream()
                .filter(f -> f.podeAtenderTipo(tipoArea))
                .sorted(Comparator.comparingInt(Funcionario::getQuantidadeServicosAtivos))
                .collect(Collectors.toList());
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        return funcionarios.get(id);
    }

    // === SERVIÇOS ===
    public boolean criarServico(String nome, String descricao, String cliente, String cep, int tipoArea, int funcionarioId) {
        Funcionario funcionario = buscarFuncionarioPorId(funcionarioId);
        
        if (funcionario == null || !funcionario.podeAtenderTipo(tipoArea)) {
            return false;
        }

        Servico servico = new Servico(nome, descricao, cliente, cep, tipoArea, funcionario);
        servicos.put(servico.getId(), servico);
        funcionario.adicionarServicoAtivo(servico);
        
        return true;
    }

    public boolean concluirServico(int servicoId) {
        Servico servico = servicos.get(servicoId);
        if (servico != null && servico.getStatus() == Servico.StatusServico.ATIVO) {
            servico.setStatus(Servico.StatusServico.CONCLUIDO);
            servico.getFuncionario().removerServicoAtivo(servico);
            return true;
        }
        return false;
    }

    public List<Servico> listarServicos() {
        return new ArrayList<>(servicos.values());
    }

    public List<Servico> listarServicosAtivos() {
        return servicos.values().stream()
                .filter(s -> s.getStatus() == Servico.StatusServico.ATIVO)
                .collect(Collectors.toList());
    }

    public List<Servico> listarServicosPorFuncionario(int funcionarioId) {
        return servicos.values().stream()
                .filter(s -> s.getFuncionario().getId() == funcionarioId)
                .collect(Collectors.toList());
    }

    // === VALIDAÇÕES ===
    public static boolean validarCEP(String cep) {
        return cep != null && cep.matches("\\d{5}-?\\d{3}");
    }

    public static boolean validarTextoNaoVazio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarTempoExperiencia(int tempo) {
        return tempo >= 0 && tempo <= 50;
    }
}