# Sistema de Cadastro de Funcionários

Sistema de gerenciamento de funcionários e serviços desenvolvido em Java, permitindo o cadastro, alocação e controle de funcionários especializados em diferentes áreas de atendimento.

## 📋 Funcionalidades

### Gerenciamento de Áreas de Especialidade
- Cadastro de áreas de especialidade com tipos únicos
- Listagem de todas as áreas disponíveis
- Validação de existência de áreas

### Gerenciamento de Funcionários
- Cadastro completo de funcionários com:
  - Nome, formação e tempo de experiência
  - Áreas de especialidade que podem atender
  - Status (Ativo, Ocupado, Inativo)
  - Controle de serviços ativos
- Listagem e busca de funcionários
- Sistema de alocação inteligente por carga de trabalho

### Gerenciamento de Serviços
- Cadastro de serviços com informações completas:
  - Nome, descrição e cliente
  - CEP com validação automática
  - Área de especialidade requerida
  - Alocação automática de funcionários qualificados
- Controle de status (Ativo, Concluído, Cancelado)
- Sistema de conclusão de serviços
- Histórico completo de serviços por funcionário

### Relatórios e Estatísticas
- Funcionários ordenados por carga de trabalho
- Histórico de serviços por funcionário
- Estatísticas gerais do sistema
- Listagem de serviços ativos

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 11 ou superior
- IDE Java (Eclipse, IntelliJ IDEA, VS Code) ou terminal

### Compilação e Execução

#### Via Terminal
```bash
# Navegar para o diretório do projeto
cd cadastro/cadastro

# Compilar o projeto
javac -d . *.java model/*.java service/*.java util/*.java view/*.java

# Executar o sistema
java Main
```

#### Via IDE
1. Importe o projeto na sua IDE
2. Execute a classe `Main.java`

## 📁 Estrutura do Projeto

```
cadastro/
├── cadastro/
│   ├── Main.java                    # Classe principal
│   ├── model/                       # Modelos de dados
│   │   ├── AreaEspecialidade.java   # Área de especialidade
│   │   ├── Funcionario.java         # Funcionário
│   │   ├── Servico.java            # Serviço
│   │   └── StatusFuncionario.java   # Enum de status
│   ├── service/                     # Lógica de negócio
│   │   └── SistemaService.java      # Serviços do sistema
│   ├── util/                        # Utilitários
│   │   └── InputUtil.java           # Utilitários de entrada
│   └── view/                        # Interface do usuário
│       └── SistemaView.java         # Interface em console
└── README.md
```

## 🎯 Como Usar

### Fluxo Recomendado
1. **Cadastrar Áreas de Especialidade**: Defina os tipos de serviços disponíveis
2. **Cadastrar Funcionários**: Registre funcionários e suas especialidades
3. **Cadastrar Serviços**: Crie serviços e aloque funcionários automaticamente
4. **Acompanhar via Relatórios**: Monitore carga de trabalho e estatísticas
5. **Concluir Serviços**: Finalize serviços quando completados

### Menu Principal
```
1. Cadastrar Áreas de Especialidade
2. Cadastrar Funcionários
3. Cadastrar Serviços
4. Listar Funcionários
5. Listar Serviços
6. Concluir Serviço
7. Relatórios
8. Ajuda
0. Sair
```

## 📊 Recursos Técnicos

### Validações Implementadas
- **CEP**: Formato brasileiro (12345-678 ou 12345678)
- **Tempo de Experiência**: Entre 0 e 50 anos
- **Campos Obrigatórios**: Validação de campos não vazios
- **Tipos de Área**: Verificação de existência antes da atribuição

### Sistema de Alocação
- Funcionários são ordenados por **menor carga de trabalho**
- Apenas funcionários **ativos** e **qualificados** são considerados
- Controle automático de serviços ativos por funcionário

### Gerenciamento de Estado
- IDs únicos auto-incrementais para funcionários e serviços
- Estados bem definidos para funcionários e serviços
- Relacionamentos bidirecionais entre funcionários e serviços

## 🔧 Características Técnicas

### Padrões Utilizados
- **MVC (Model-View-Controller)**: Separação clara de responsabilidades
- **Service Layer**: Lógica de negócio centralizada
- **Utility Classes**: Reutilização de código para entrada de dados

### Estruturas de Dados
- `HashMap` para busca eficiente por ID
- `ArrayList` para listas ordenáveis
- `Stream API` para filtragem e ordenação

### Funcionalidades Java
- **Enums** para status bem definidos
- **LocalDateTime** para timestamps
- **Stream API** para operações funcionais
- **Collections Framework** para estruturas de dados

## 📝 Exemplos de Uso

### Cadastro de Área
```
Digite o nome da área de especialidade: Desenvolvimento Web
Área cadastrada com sucesso! (Tipo: 1)
```

### Cadastro de Funcionário
```
Nome do funcionário: João Silva
Formação: Engenheiro de Software
Tempo de experiência (anos): 5
Informe o tipo da área que ele atende: 1
```

### Cadastro de Serviço
```
Nome do serviço: Sistema E-commerce
Descrição do serviço: Desenvolvimento de loja virtual
Nome do cliente: Empresa XYZ
CEP do cliente: 13330-250
Tipo da área (número): 1
```

## 🎨 Interface

O sistema utiliza interface de console com:
- Menus numerados intuitivos
- Validação de entrada em tempo real
- Formatação clara com separadores visuais
- Mensagens de erro e sucesso informativas
- Sistema de confirmação para operações importantes

## 🔄 Status do Projeto

✅ **Funcional** - Sistema completo e operacional

### Recursos Implementados
- [x] Cadastro de áreas de especialidade
- [x] Cadastro de funcionários com especialidades
- [x] Sistema de alocação de serviços
- [x] Controle de status de funcionários e serviços
- [x] Relatórios e estatísticas
- [x] Validações de entrada
- [x] Interface de console completa

## 📄 Licença

Este projeto é de uso acadêmico/educacional.

## 👥 Contribuição

Para contribuir com o projeto:
1. Faça um fork do repositório
2. Crie uma branch para sua feature
3. Implemente suas modificações
4. Teste as funcionalidades
5. Submeta um pull request

---

**Desenvolvido em Java** ☕ | **Sistema de Console** 💻 | **Padrão MVC** 🏗️
