package view;

import model.*;
import service.SistemaService;
import util.InputUtil;
import java.util.List;

public class SistemaView {
    private SistemaService service;

    public SistemaView() {
        this.service = new SistemaService();
    }

    public void iniciar() {
        System.out.println("=== SISTEMA DE CADASTRO DE FUNCIONARIOS ===");
        
        while (true) {
            exibirMenuPrincipal();
            int opcao = InputUtil.lerOpcaoMenu("Escolha uma opcao: ", 0, 8);
            
            switch (opcao) {
                case 1 -> cadastrarAreas();
                case 2 -> cadastrarFuncionarios();
                case 3 -> cadastrarServicos();
                case 4 -> listarFuncionarios();
                case 5 -> listarServicos();
                case 6 -> concluirServico();
                case 7 -> exibirRelatorios();
                case 8 -> exibirAjuda();
                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    return;
                }
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MENU PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. Cadastrar Areas de Especialidade");
        System.out.println("2. Cadastrar Funcionarios");
        System.out.println("3. Cadastrar Servicos");
        System.out.println("4. Listar Funcionários");
        System.out.println("5. Listar Servicos");
        System.out.println("6. Concluir Servico");
        System.out.println("7. Relatórios");
        System.out.println("8. Ajuda");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
    }

    private void cadastrarAreas() {
        System.out.println("\n=== CADASTRO DE AREAS DE ESPECIALIDADE ===");
        
        int tipo = 1;
        do {
            String nome = InputUtil.lerTextoObrigatorio("Digite o nome da area de especialidade: ");
            service.adicionarArea(new AreaEspecialidade(tipo, nome));
            System.out.println("Area cadastrada com sucesso! (Tipo: " + tipo + ")");
            tipo++;
        } while (InputUtil.lerConfirmacao("Deseja adicionar mais uma area?"));
        
        System.out.println("Cadastro de areas concluído!");
    }

    private void cadastrarFuncionarios() {
        System.out.println("\n=== CADASTRO DE FUNCIONARIOS ===");
        
        List<AreaEspecialidade> areas = service.listarAreas();
        if (areas.isEmpty()) {
            System.out.println("Nenhuma area cadastrada! Cadastre areas primeiro.");
            return;
        }

        do {
            System.out.println("\n--- Novo Funcionario ---");
            String nome = InputUtil.lerTextoObrigatorio("Nome do funcionario: ");
            String formacao = InputUtil.lerTextoObrigatorio("Formacao: ");
            
            int tempoExperiencia;
            do {
                tempoExperiencia = InputUtil.lerInteiroPositivo("Tempo de experiencia (anos): ");
                if (!SistemaService.validarTempoExperiencia(tempoExperiencia)) {
                    System.out.println("Tempo de experiência deve estar entre 0 e 50 anos!");
                }
            } while (!SistemaService.validarTempoExperiencia(tempoExperiencia));

            Funcionario funcionario = new Funcionario(nome, formacao, tempoExperiencia);

            // Exibir áreas disponíveis
            System.out.println("\nAreas disponiveis:");
            for (AreaEspecialidade area : areas) {
                System.out.println(area);
            }

            // Adicionar tipos que o funcionário atende
            do {
                int tipo = InputUtil.lerInteiroPositivo("Informe o tipo da area que ele atende: ");
                if (service.existeArea(tipo)) {
                    funcionario.adicionarTipoAtendido(tipo);
                    System.out.println("Tipo adicionado com sucesso!");
                } else {
                    System.out.println("Tipo de area nao existe!");
                }
            } while (InputUtil.lerConfirmacao("Deseja adicionar outro tipo?"));

            service.adicionarFuncionario(funcionario);
            System.out.println("Funcionario cadastrado com sucesso!");
            
        } while (InputUtil.lerConfirmacao("Deseja cadastrar mais um funcionario?"));
    }

    private void cadastrarServicos() {
        System.out.println("\n=== CADASTRO DE SERVICOS ===");
        
        if (service.listarFuncionarios().isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado! Cadastre funcionarios primeiro.");
            return;
        }

        do {
            System.out.println("\n--- Novo Servico ---");
            String nome = InputUtil.lerTextoObrigatorio("Nome do servico: ");
            String descricao = InputUtil.lerTextoObrigatorio("Descricao do servico: ");
            String cliente = InputUtil.lerTextoObrigatorio("Nome do cliente: ");
            
            String cep;
            do {
                cep = InputUtil.lerTextoObrigatorio("CEP do cliente: ");
                if (!SistemaService.validarCEP(cep)) {
                    System.out.println("CEP invalido! Use o formato: 12345-678 ou 12345678");
                }
            } while (!SistemaService.validarCEP(cep));

            int tipoArea = InputUtil.lerInteiroPositivo("Tipo da area (numero): ");
            
            if (!service.existeArea(tipoArea)) {
                System.out.println("Tipo de area nao existe!");
                continue;
            }

            List<Funcionario> disponiveis = service.buscarFuncionariosDisponiveis(tipoArea);
            
            if (disponiveis.isEmpty()) {
                System.out.println("Nenhum funcionario disponivel para esse tipo de servico.");
                continue;
            }

            System.out.println("\nFuncionarios disponiveis (ordenados por carga de trabalho):");
            for (int i = 0; i < disponiveis.size(); i++) {
                System.out.println((i + 1) + " - " + disponiveis.get(i));
            }

            int escolha = InputUtil.lerOpcaoMenu("Escolha o numero do funcionario: ", 1, disponiveis.size()) - 1;
            Funcionario funcionarioEscolhido = disponiveis.get(escolha);

            if (service.criarServico(nome, descricao, cliente, cep, tipoArea, funcionarioEscolhido.getId())) {
                System.out.println("Servico cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar serviço!");
            }

        } while (InputUtil.lerConfirmacao("Deseja cadastrar outro servico?"));
    }

    private void listarFuncionarios() {
        System.out.println("\n=== LISTA DE FUNCIONARIOS ===");
        List<Funcionario> funcionarios = service.listarFuncionarios();
        
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        for (Funcionario f : funcionarios) {
            System.out.println(f);
            System.out.println("Tipos atendidos: " + f.getTiposAtendidos());
            System.out.println("-".repeat(50));
        }
    }

    private void listarServicos() {
        System.out.println("\n=== LISTA DE SERVICOS ===");
        List<Servico> servicos = service.listarServicos();
        
        if (servicos.isEmpty()) {
            System.out.println("Nenhum servico cadastrado.");
            return;
        }

        for (Servico s : servicos) {
            System.out.println(s);
            System.out.println("-".repeat(60));
        }
    }

    private void concluirServico() {
        System.out.println("\n=== CONCLUIR SERVICO ===");
        List<Servico> servicosAtivos = service.listarServicosAtivos();
        
        if (servicosAtivos.isEmpty()) {
            System.out.println("Nenhum servico ativo encontrado.");
            return;
        }

        System.out.println("Servicos ativos:");
        for (int i = 0; i < servicosAtivos.size(); i++) {
            Servico s = servicosAtivos.get(i);
            System.out.printf("%d - %s (Cliente: %s, Funcionario: %s)\n", 
                    i + 1, s.getNome(), s.getCliente(), s.getFuncionario().getNome());
        }

        int escolha = InputUtil.lerOpcaoMenu("Escolha o servico para concluir: ", 1, servicosAtivos.size()) - 1;
        Servico servico = servicosAtivos.get(escolha);

        if (service.concluirServico(servico.getId())) {
            System.out.println("Servico concluido com sucesso!");
        } else {
            System.out.println("Erro ao concluir serviço!");
        }
    }

    private void exibirRelatorios() {
        System.out.println("\n=== RELATORIOS ===");
        System.out.println("1. Funcionarios por carga de trabalho");
        System.out.println("2. Serviços por funcionario");
        System.out.println("3. Estatisticas gerais");
        
        int opcao = InputUtil.lerOpcaoMenu("Escolha o relatorio: ", 1, 3);
        
        switch (opcao) {
            case 1 -> relatorioFuncionariosPorCarga();
            case 2 -> relatorioServicosPorFuncionario();
            case 3 -> relatorioEstatisticas();
        }
    }

    private void relatorioFuncionariosPorCarga() {
        System.out.println("\n=== FUNCIONARIOS POR CARGA DE TRABALHO ===");
        List<Funcionario> funcionarios = service.listarFuncionarios();
        funcionarios.sort((f1, f2) -> Integer.compare(f2.getQuantidadeServicosAtivos(), f1.getQuantidadeServicosAtivos()));
        
        for (Funcionario f : funcionarios) {
            System.out.printf("%s - %d serviços ativos\n", f.getNome(), f.getQuantidadeServicosAtivos());
        }
    }

    private void relatorioServicosPorFuncionario() {
        int funcionarioId = InputUtil.lerInteiroPositivo("ID do funcionario: ");
        Funcionario funcionario = service.buscarFuncionarioPorId(funcionarioId);
        
        if (funcionario == null) {
            System.out.println("Funcionario não encontrado!");
            return;
        }

        System.out.println("\n=== SERVIÇOS DE " + funcionario.getNome().toUpperCase() + " ===");
        List<Servico> servicos = service.listarServicosPorFuncionario(funcionarioId);
        
        for (Servico s : servicos) {
            System.out.printf("%s - %s - Status: %s\n", s.getNome(), s.getCliente(), s.getStatus());
        }
    }

    private void relatorioEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS GERAIS ===");
        System.out.println("Total de areas: " + service.listarAreas().size());
        System.out.println("Total de funcionarios: " + service.listarFuncionarios().size());
        System.out.println("Total de serviços: " + service.listarServicos().size());
        System.out.println("Serviços ativos: " + service.listarServicosAtivos().size());
    }

    private void exibirAjuda() {
        System.out.println("\n=== AJUDA ===");
        System.out.println("Este sistema permite gerenciar funcionarios e serviços.");
        System.out.println("Fluxo recomendado:");
        System.out.println("1. Cadastre as areas de especialidade");
        System.out.println("2. Cadastre os funcionarios e suas especialidades");
        System.out.println("3. Cadastre os serviços e aloque funcionarios");
        System.out.println("4. Use os relatarios para acompanhar o sistema");
        System.out.println("5. Conclua serviços quando terminados");
        System.out.println("\nFormato CEP: 12345-678 ou 12345678");
        System.out.println("Os funcionarios não são removidos ao serem alocados!");
    }
}