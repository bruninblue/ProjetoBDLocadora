import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.Utils;
import entidades.Cliente;
import entidades.Filme;
import entidades.Genero;
import entidades.Aluguel;
import entidades.Acervo;
import entidades.ItemLocacao;
import entidadesDAO.ClienteDAO;
import entidadesDAO.FilmeDAO;
import entidadesDAO.GeneroDAO;
import entidadesDAO.AluguelDAO;
import entidadesDAO.AcervoDAO;
import entidadesDAO.ItemLocacaoDAO;

public class App {

    public static Scanner leia = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("Bem-vindo a locadora de filmes, qual a sua categoria?");
            System.out.println("1 - Cliente");
            System.out.println("2 - Filmes");
            System.out.println("3 - Aluguel");
            System.out.println("4 - Gêneros");
            System.out.println("5 - Acervo");
            System.out.println("6 - Sair");

            System.out.println("Digite uma opção: ");
            opcao = leia.nextInt();

            switch (opcao) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuFilmes();
                    break;
                case 3:
                    menuAluguel();
                    break;
                case 4:
                    menuGeneros();
                    break;
                case 5:
                    menuAcervo();
                default:
                    return;
            }
        }
    }

    public static void menuCliente() {

        int opcao = 0;

        do {
            System.out.println("Menu de Cliente");
            System.out.println("1 - Cadastrar novo cliente");
            System.out.println("2 - Listar clientes cadastrados");
            System.out.println("3 - Excluir cliente");
            System.out.println("4 - Alterar dados do cliente");
            System.out.println("5 - Voltar ao menu principal");

            System.out.println("Digite uma opção: ");
            opcao = leia.nextInt();

            switch (opcao) {
                case 1:
                    inserirCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    excluirCliente();
                    break;
                case 4:
                    alterarCliente();
                    break;
                case 5:
                    menuPrincipal();
                    break;
            }
        } while (opcao < 1 || opcao > 5);

    }

    public static void menuFilmes() {
        int opcao = 0;
        do {
            System.out.println("\n--- MENU DE FILMES ---");
            System.out.println("1 - Cadastrar novo filme");
            System.out.println("2 - Listar filmes cadastrados");
            System.out.println("3 - Alterar dados do filme");
            System.out.println("4 - Excluir filme");
            System.out.println("5 - Voltar ao menu principal");

            System.out.print("Digite uma opção: ");
            opcao = leia.nextInt();

            switch (opcao) {
                case 1:
                    inserirFilme();
                    break;
                case 2:
                    listarFilme();
                    break;
                case 3:
                    alterarFilme();
                    break;
                case 4:
                    excluirFilme();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (true);
    }

    public static void menuAluguel() {
        int opcao = 0;

        do {
            System.out.println("\n--- MENU DE ALUGUEL ---");
            System.out.println("1 - Realizar nova locação");
            System.out.println("2 - Realizar devolução");
            System.out.println("3 - Listar locações");
            System.out.println("4 - Excluir locação");
            System.out.println("5 - Voltar ao menu principal");

            System.out.print("Digite uma opção: ");
            opcao = leia.nextInt();

            switch (opcao) {
                case 1:
                    realizarLocacao();
                    break;
                case 2:
                    realizarDevolucao();
                    break;
                case 3:
                    listarLocacoes();
                    break;
                case 4:
                    // excluirLocacao();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (true);
    }

    public static void menuGeneros() {
        int opcao = 0;
        do {
            System.out.println("\n--- MENU DE GÊNEROS ---");
            System.out.println("1 - Criar um novo gênero");
            System.out.println("2 - Listar gêneros");
            System.out.println("3 - Deletar gênero");
            System.out.println("4 - Voltar ao menu principal");

            System.out.print("Digite uma opção: ");
            opcao = leia.nextInt();

            switch (opcao) {
                case 1:
                    inserirGenero();
                    break;
                case 2:
                    listarGeneros();
                    break;
                case 3:
                    deletarGenero();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (true);
    }

    // -----------Cliente--------------
    public static void inserirCliente() {
        // Adicionar verificações
        System.out.println("Digite seu CPF: ");
        leia.nextLine(); // limpeza buffer;
        String cpf = leia.nextLine();

        if (Utils.verificarExistenciaCliente(cpf) == 0) {
            System.out.println("Digite seu nome completo: ");
            String nome_completo = leia.nextLine();

            System.out.println("Digite seu número de telefone: ");
            String telefone = leia.nextLine();

            Cliente clienteNovo = new Cliente();
            clienteNovo.setCpf(cpf);
            clienteNovo.setNomeCompleto(nome_completo);
            clienteNovo.setNumTelefone(telefone);

            if (new ClienteDAO().inserirCliente(clienteNovo)) {
                System.out.println("Cadastrado com sucesso.");
            } else {
                System.out.println("Não foi possível cadastrar o cliente.");
            }
        } else {
            System.out.println("Um cliente com o CPF digitado já existe no sistema!");
        }
    }

    public static void listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes = new ClienteDAO().listarClientes();

        for (Cliente cli : clientes) {
            System.out.println("--------------------------------");
            System.out.println("Nome: " + cli.getNomeCompleto());
            System.out.println("CPF: " + cli.getCpf());
            System.out.println("Telefone:  " + cli.getNumTelefone());
        }

    }

    public static void excluirCliente() {
        System.out.println("Digite o CPF do cliente que deseja deletar do sistema: ");
        leia.nextLine();// limpar buffer
        String cpf = leia.nextLine();

        if (Utils.verificarExistenciaCliente(cpf) == 1) {

            if (new ClienteDAO().deletarCliente(cpf)) {
                System.out.println("Exclusão feita com sucesso!");
            } else {
                System.out.println("Não foi possível deletar o cliente!");
            }

        } else {

            System.out.println("Não foi possível encontrar o CPF digitado!");
        }
    }

    public static void alterarCliente() {
        System.out.println("Qual informação deseja altera?");
        System.out.println("1 - Nome completo");
        System.out.println("2 - Número de telefone");
        System.out.println("3 - Os dois dados");
        int opcao = leia.nextInt();

        System.out.println("Qual o cpf do cliente que deseja alterar?");
        leia.nextLine();// limpar buffer
        String cpf = leia.nextLine();

        if (Utils.verificarExistenciaCliente(cpf) == 1) {
            if (opcao == 1) {
                System.out.println("Digite o novo nome completo: ");
                String novoNome = leia.nextLine();
                Cliente cli = new Cliente();
                cli.setCpf(cpf);
                cli.setNomeCompleto(novoNome);

                if (new ClienteDAO().alterarDadosCliente(cli, opcao)) {
                    System.out.println("Alteração feita com sucesso");
                } else {
                    System.out.println("Não foi possível realizar a alteração");
                }

            } else if (opcao == 2) {
                System.out.println("Digite o novo número de telefone: ");
                String novoTelefone = leia.nextLine();
                Cliente cli = new Cliente();
                cli.setCpf(cpf);
                cli.setNumTelefone(novoTelefone);

                if (new ClienteDAO().alterarDadosCliente(cli, opcao)) {
                    System.out.println("Alteração feita com sucesso");
                } else {
                    System.out.println("Não foi possível realizar a alteração");
                }
            } else if (opcao == 3) {
                System.out.println("Digite o novo nome completo: ");
                String novoNome = leia.nextLine();
                System.out.println("Digite o novo número de telefone");
                String novoTelefone = leia.nextLine();
                Cliente cli = new Cliente();
                cli.setCpf(cpf);
                cli.setNomeCompleto(novoNome);
                cli.setNumTelefone(novoTelefone);

                if (new ClienteDAO().alterarDadosCliente(cli, opcao)) {
                    System.out.println("Alteração feita com sucesso");
                } else {
                    System.out.println("Não foi possível realizar a alteração!");
                }
            }
        } else {
            System.out.println("CPF digitado não foi encontrado");
        }

    }

    // ----------Filmes-----------------
    public static void inserirFilme() {
        System.out.println("Digite o título do filme: ");
        leia.nextLine();
        String titulo = leia.nextLine();

        System.out.println("Digite a data de lançamento do filme (AAAA-MM-DD): ");
        String dataLancamento = leia.nextLine();

        listarGeneros();
        System.out.println("Digite o gênero do filme: ");
        int genero = leia.nextInt();
        GeneroDAO generoDAO = new GeneroDAO();
        while (generoDAO.buscarGeneroPorId(genero) == null) {
            System.out.println("Gênero não encontrado. Por favor, digite um ID de gênero válido: ");
            genero = leia.nextInt();
        }

        System.out.println("Digite o valor do aluguel do filme: ");
        float valor = leia.nextFloat();

        Filme novoFilme = new Filme();
        novoFilme.setTitulo(titulo);
        novoFilme.setDataLancamento(Date.valueOf(dataLancamento));
        novoFilme.setGenero(genero);
        novoFilme.setValor(valor);

        if (new FilmeDAO().criarFilme(novoFilme)) {
            System.out.println("Filme cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar o filme.");
        }
    }

    public static void listarFilme() {
        List<Filme> listaFilmes = new FilmeDAO().listarFilmes();
        GeneroDAO generoDAO = new GeneroDAO();

        if (listaFilmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            for (Filme filme : listaFilmes) {
                Genero genero = generoDAO.buscarGeneroPorId(filme.getGenero());
                String tipoGenero = (genero != null) ? genero.getTipo_genero() : "Desconhecido";
                System.out.println("-----------------------");
                System.out.println("ID: " + filme.getId());
                System.out.println("Título: " + filme.getTitulo());
                System.out.println("Data de Lançamento: " + filme.getDataLancamento());
                System.out.println("Gênero: " + tipoGenero);
                System.out.println("Valor: " + filme.getValor());
            }
        }
    }

    public static void alterarFilme() {
        listarFilme();

        FilmeDAO filmeDAO = new FilmeDAO();

        System.out.println("-----------------------");
        System.out.println("Digite o ID do filme que deseja alterar: ");
        int id = leia.nextInt();
        leia.nextLine();

        Filme filme = filmeDAO.buscarFilmePorId(id);
        if (filme == null) {
            System.out.println("Filme não encontrado.");
            menuFilmes();
            return;
        } else {
            System.out.println("-----------------------");
            System.out.println("Filme encontrado: ");
            System.out.println("ID: " + filme.getId());
            System.out.println("Título: " + filme.getTitulo());
            System.out.println("Data de Lançamento: " + filme.getDataLancamento());
            System.out.println("Gênero: " + filme.getGenero());
            System.out.println("Valor: " + filme.getValor());
            System.out.println("-----------------------");
        }
        System.out.println("Qual informação deseja alterar?");
        System.out.println("1 - Título");
        System.out.println("2 - Data de Lançamento");
        System.out.println("3 - Gênero");
        System.out.println("4 - Valor");
        int opcao = leia.nextInt();
        leia.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo título: ");
                String novoTitulo = leia.nextLine();
                filme.setTitulo(novoTitulo);
                break;
            case 2:
                System.out.println("Digite a nova data de lançamento (AAAA-MM-DD): ");
                String novaData = leia.nextLine();
                filme.setDataLancamento(Date.valueOf(novaData));
                break;
            case 3:
                System.out.println("Digite o novo gênero: ");
                int novoGenero = leia.nextInt();
                filme.setGenero(novoGenero);
                break;
            case 4:
                System.out.println("Digite o novo valor: ");
                float novoValor = leia.nextFloat();
                filme.setValor(novoValor);
                break;
            case 5:
                System.out.println("Digite o novo título: ");
                filme.setTitulo(leia.nextLine());
                System.out.println("Digite a nova data de lançamento (AAAA-MM-DD): ");
                filme.setDataLancamento(Date.valueOf(leia.nextLine()));
                System.out.println("Digite o novo gênero: ");
                filme.setGenero(leia.nextInt());
                System.out.println("Digite o novo valor: ");
                filme.setValor(leia.nextFloat());
            default:
                System.out.println("Opção inválida.");
                return;
        }

    }

    public static void excluirFilme() {
        listarFilme();
        System.out.println("Digite o ID do filme que deseja excluir: ");
        int id = leia.nextInt();
        if (new FilmeDAO().excluirFilme(id)) {
            System.out.println("Filme excluído com sucesso!");
        } else {
            System.out.println("Não foi possível excluir o filme.");
        }

    }

    // ----------Gênero----------------
    public static void inserirGenero() {
        System.out.println("Digite o tipo de gênero: ");
        leia.nextLine();
        String tipoGenero = leia.nextLine();

        // Verifica se já existe um gênero com esse nome (ignorando
        // maiúsculas/minúsculas)
        ArrayList<Genero> listaGeneros = new GeneroDAO().listarGeneros();
        boolean existe = listaGeneros.stream()
                .anyMatch(g -> g.getTipo_genero().equalsIgnoreCase(tipoGenero));

        if (existe) {
            System.out.println("Já existe um gênero com esse nome cadastrado!");
            return;
        }

        Genero novoGenero = new Genero();
        novoGenero.setTipo_genero(tipoGenero);

        if (new GeneroDAO().criarGenero(novoGenero)) {
            System.out.println("Gênero cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar o gênero.");
        }
    }

    public static void listarGeneros() {
        ArrayList<Genero> listaGeneros = new ArrayList<>();
        listaGeneros = new GeneroDAO().listarGeneros();

        for (Genero gen : listaGeneros) {
            System.out.println("-----------------------");
            System.out.println("ID: " + gen.getId());
            System.out.println("Tipo: " + gen.getTipo_genero());
        }
    }

    public static void deletarGenero() {
        GeneroDAO generoDAO = new GeneroDAO();
        ArrayList<Genero> listaGeneros = generoDAO.listarGeneros();

        if (listaGeneros.isEmpty()) {
            System.out.println("Nenhum gênero cadastrado para excluir.");
            return;
        }

        System.out.println("\n--- Gêneros Cadastrados ---");
        for (Genero gen : listaGeneros) {
            System.out.println("ID: " + gen.getId() + " | Tipo: " + gen.getTipo_genero());
        }

        System.out.print("Digite o ID do gênero que deseja excluir: ");
        int id = leia.nextInt();

        Genero generoSelecionado = generoDAO.buscarGeneroPorId(id);
        if (generoSelecionado == null) {
            System.out.println("Gênero com ID informado não encontrado.");
            return;
        }

        System.out
                .print("Tem certeza que deseja excluir o gênero '" + generoSelecionado.getTipo_genero() + "'? (s/n): ");
        leia.nextLine(); // limpa buffer
        String confirmacao = leia.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            if (generoDAO.excluirGenero(id)) {
                System.out.println("Gênero excluído com sucesso.");
            } else {
                System.out.println("Erro ao excluir o gênero.");
            }
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    // ----------Aluguel----------------
    public static void realizarLocacao() {
        ClienteDAO clienteDAO = new ClienteDAO();
        FilmeDAO filmeDAO = new FilmeDAO();
        AcervoDAO acervoDAO = new AcervoDAO();
        AluguelDAO aluguelDAO = new AluguelDAO();
        ItemLocacaoDAO itemLocacaoDAO = new ItemLocacaoDAO();

        System.out.println("Digite o CPF do cliente: ");
        leia.nextLine(); // limpar buffer
        String cpf = leia.nextLine();

        if (Utils.verificarExistenciaCliente(cpf) == 0) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Criar o aluguel com data de hoje e devolução em até 7 dias (exemplo)
        Aluguel aluguel = new Aluguel();
        aluguel.setClienteCpf(cpf);
        aluguel.setDataAluguel(new Date(System.currentTimeMillis()));
        aluguel.setDataDevolucao(Utils.somarDias(7)); // função auxiliar que soma dias
        aluguel.setValorPagar(0); // será atualizado depois
        aluguel.setMulta(0);
        aluguel.setPendente(1); // pendente

        if (!aluguelDAO.inserirAluguel(aluguel)) {
            System.out.println("Erro ao criar a locação.");
            return;
        }

        float total = 0;
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nFilmes disponíveis para locação:");
            List<Filme> filmes = filmeDAO.listarFilmes();
            for (Filme filme : filmes) {
                System.out.println("ID: " + filme.getId() +
                        " | Título: " + filme.getTitulo() +
                        " | Valor: R$" + filme.getValor());
            }

            System.out.print("Digite o ID do filme que deseja alugar: ");
            int idFilmeEscolhido = leia.nextInt();

            // Buscar acervos DISPONÍVEIS desse filme
            List<Acervo> acervosDisponiveis = acervoDAO.listarAcervosPorSituacao(Acervo.Situacao.DISPONIVEL)
                    .stream()
                    .filter(a -> a.getFilmeId() == idFilmeEscolhido)
                    .toList();

            if (acervosDisponiveis.isEmpty()) {
                System.out.println("Não há exemplares disponíveis desse filme para locação.");
            } else {
                Acervo acervoParaAlugar = acervosDisponiveis.get(0); // pega o primeiro disponível

                // Inserir em itemlocacao
                ItemLocacao item = new ItemLocacao();
                item.setIdLocacao(aluguel.getId());
                item.setIdAcervo(acervoParaAlugar.getIdAcervo());

                if (itemLocacaoDAO.inserirItemLocacao(item)) {
                    System.out.println("Filme adicionado à locação!");
                    total += filmeDAO.buscarFilmePorId(idFilmeEscolhido).getValor();

                    // Atualiza situação para ALUGADO
                    acervoDAO.alterarSituacao(acervoParaAlugar.getIdAcervo(), Acervo.Situacao.ALUGADO);
                } else {
                    System.out.println("Erro ao adicionar item à locação.");
                }
            }

            System.out.println("Deseja alugar outro filme? (s/n): ");
            leia.nextLine(); // limpar buffer
            String resp = leia.nextLine();
            continuar = resp.equalsIgnoreCase("s");
        }

        // Atualizar valor total
        aluguelDAO.atualizarValorFinalEMulta(aluguel.getId(), total, 0, 1);
        System.out.println("Locação realizada com sucesso. Valor total: R$" + total);
    }

    public static void realizarDevolucao() {
        AluguelDAO aluguelDAO = new AluguelDAO();
        ItemLocacaoDAO itemLocacaoDAO = new ItemLocacaoDAO();
        AcervoDAO acervoDAO = new AcervoDAO();
        FilmeDAO filmeDAO = new FilmeDAO();

        System.out.print("Digite o CPF do cliente: ");
        leia.nextLine(); // limpar buffer
        String cpf = leia.nextLine();

        if (Utils.verificarExistenciaCliente(cpf) == 0) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        ArrayList<Aluguel> alugueis = aluguelDAO.listarAlugueis();
        List<Aluguel> alugueisDoCliente = alugueis.stream()
                .filter(a -> a.getClienteCpf().equals(cpf) && a.getPendente() == 1)
                .toList();

        if (alugueisDoCliente.isEmpty()) {
            System.out.println("Este cliente não possui locações pendentes.");
            return;
        }

        System.out.println("\nLocações pendentes:");
        for (Aluguel a : alugueisDoCliente) {
            System.out.println("\nID Locação: " + a.getId());
            List<ItemLocacao> itens = itemLocacaoDAO.listarItensPorLocacao(a.getId());

            for (ItemLocacao item : itens) {
                int idAcervo = item.getIdAcervo();
                Acervo acervo = acervoDAO.buscarAcervoPorId(idAcervo);

                if (acervo != null) {
                    Filme filme = filmeDAO.buscarFilmePorId(acervo.getFilmeId());
                    System.out.println("  - Filme: " + filme.getTitulo());
                }
            }
        }

        System.out.print("\nDigite o ID da locação que deseja devolver: ");
        int idLocacao = leia.nextInt();
        Aluguel aluguelSelecionado = aluguelDAO.listarAluguelPorId(idLocacao);

        if (aluguelSelecionado == null ||
                !aluguelSelecionado.getClienteCpf().equals(cpf) ||
                aluguelSelecionado.getPendente() != 1) {
            System.out.println("ID de locação inválido.");
            return;
        }

        ArrayList<ItemLocacao> itensParaDevolver = itemLocacaoDAO.listarItensPorLocacao(idLocacao);
        float total = 0;

        for (ItemLocacao item : itensParaDevolver) {
            int idAcervo = item.getIdAcervo();

            // Liberar acervo
            acervoDAO.alterarSituacao(idAcervo, Acervo.Situacao.DISPONIVEL);

            // Somar valor do filme
            Acervo acervo = acervoDAO.buscarAcervoPorId(idAcervo);

            if (acervo != null) {
                Filme filme = filmeDAO.buscarFilmePorId(acervo.getFilmeId());
                total += filme.getValor();
            }
        }

        // Calcular multa (1 real por dia de atraso)
        long diasAtraso = (System.currentTimeMillis() - aluguelSelecionado.getDataDevolucao().getTime())
                / (1000 * 60 * 60 * 24);
        float multa = diasAtraso > 0 ? diasAtraso * 1.0f : 0.0f;

        aluguelDAO.atualizarValorFinalEMulta(idLocacao, total + multa, multa, 0);

        System.out.println("\nDevolução concluída com sucesso!");
        System.out.println("Valor total da locação: R$" + total);
        if (multa > 0) {
            System.out.println("Multa por atraso: R$" + multa);
        }
    }

    public static void listarLocacoes() {
        AluguelDAO aluguelDAO = new AluguelDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        FilmeDAO filmeDAO = new FilmeDAO();
        ItemLocacaoDAO itemDAO = new ItemLocacaoDAO();
        AcervoDAO acervoDAO = new AcervoDAO();

        ArrayList<Aluguel> alugueis = aluguelDAO.listarAlugueis();
        ArrayList<Cliente> clientes = clienteDAO.listarClientes();

        if (alugueis.isEmpty()) {
            System.out.println("Nenhuma locação encontrada.");
            return;
        }

        for (Aluguel aluguel : alugueis) {
            System.out.println("\n=============================");
            System.out.println("ID Locação: " + aluguel.getId());
            System.out.println("CPF Cliente: " + aluguel.getClienteCpf());

            Cliente cli = clientes.stream()
                    .filter(c -> c.getCpf().equals(aluguel.getClienteCpf()))
                    .findFirst()
                    .orElse(null);

            if (cli != null) {
                System.out.println("Nome: " + cli.getNomeCompleto());
            }

            System.out.println("Data Aluguel: " + aluguel.getDataAluguel());
            System.out.println("Data Devolução: " + aluguel.getDataDevolucao());
            System.out.println("Valor Final: R$" + aluguel.getValorPagar());
            System.out.println("Multa: R$" + aluguel.getMulta());
            System.out.println("Status: " + (aluguel.getPendente() == 1 ? "PENDENTE" : "DEVOLVIDO"));

            List<ItemLocacao> itens = itemDAO.listarItensPorLocacao(aluguel.getId());
            if (!itens.isEmpty()) {
                System.out.println("Filmes alugados:");
                for (ItemLocacao item : itens) {
                    Acervo acervo = acervoDAO.buscarAcervoPorId(item.getIdAcervo());
                    if (acervo != null) {
                        Filme filme = filmeDAO.buscarFilmePorId(acervo.getFilmeId());
                        System.out.println("  - " + (filme != null ? filme.getTitulo() : "Filme não encontrado"));
                    }
                }
            } else {
                System.out.println("Nenhum item associado à locação.");
            }
        }
    }
    
    public static void menuAcervo() {
        AcervoDAO acervoDAO = new AcervoDAO();
        FilmeDAO filmeDAO = new FilmeDAO();
        int opcao = 0;

        do {
            System.out.println("\n--- MENU DE ACERVO ---");
            System.out.println("1 - Cadastrar novo exemplar de filme");
            System.out.println("2 - Listar exemplares (acervo)");
            System.out.println("3 - Alterar situação de um exemplar");
            System.out.println("4 - Excluir exemplar");
            System.out.println("5 - Voltar ao menu principal");

            System.out.print("Digite uma opção: ");
            opcao = leia.nextInt();

            switch (opcao) {
                case 1:
                    listarFilme(); // exibir filmes disponíveis
                    System.out.print("Digite o ID do filme para adicionar ao acervo: ");
                    int idFilme = leia.nextInt();

                    Acervo novoAcervo = new Acervo();
                    novoAcervo.setFilmeId(idFilme);
                    novoAcervo.setSituacao(Acervo.Situacao.DISPONIVEL);

                    if (acervoDAO.inserirNoAcervo(novoAcervo)) {
                        System.out.println("Exemplar adicionado ao acervo com sucesso.");
                    } else {
                        System.out.println("Erro ao adicionar exemplar.");
                    }
                    break;

                case 2:
                    List<Acervo> acervos = acervoDAO.listarAcervos();
                    if (acervos.isEmpty()) {
                        System.out.println("Nenhum exemplar cadastrado no acervo.");
                    } else {
                        System.out.println("\n--- Exemplares no Acervo ---");
                        for (Acervo a : acervos) {
                            Filme f = filmeDAO.buscarFilmePorId(a.getFilmeId());
                            System.out.println("ID Acervo: " + a.getIdAcervo() +
                                    " | Filme: " + (f != null ? f.getTitulo() : "Desconhecido") +
                                    " | Situação: " + a.getSituacao());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do acervo que deseja alterar: ");
                    int idAlterar = leia.nextInt();

                    System.out.println("Selecione a nova situação:");
                    System.out.println("1 - DISPONIVEL");
                    System.out.println("2 - ALUGADO");
                    int situacao = leia.nextInt();
                    Acervo.Situacao novaSituacao = (situacao == 2) ? Acervo.Situacao.ALUGADO
                            : Acervo.Situacao.DISPONIVEL;

                    if (acervoDAO.alterarSituacao(idAlterar, novaSituacao)) {
                        System.out.println("Situação alterada com sucesso.");
                    } else {
                        System.out.println("Erro ao alterar situação.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID do acervo que deseja excluir: ");
                    int idExcluir = leia.nextInt();

                    if (acervoDAO.deletarAcervo(idExcluir)) {
                        System.out.println("Exemplar excluído com sucesso.");
                    } else {
                        System.out.println("Erro ao excluir exemplar.");
                    }
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (true);
    }

}
