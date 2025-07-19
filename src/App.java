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
            System.out.println("5 - Sair");

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
        System.out.println("Menu de Filmes");
        System.out.println("1 - Cadastrar novo filme");
        System.out.println("2 - Listar filmes cadastrados");
        System.out.println("3 - Alterar dados do filme");
        System.out.println("4 - Excluir filme");

        int opcao = leia.nextInt();

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
        }

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
                    // realizarLocacao();
                    break;
                case 2:
                    // realizarDevolucao();
                    break;
                case 3:
                    // listarAlugueis();
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
        System.out.println("Menu de Gêneros");
        System.out.println("1 - Criar um novo gênero");
        System.out.println("2 - Listar gênero");
        System.out.println("3 - Deletar gênero");

        int opcao = leia.nextInt();

        switch (opcao) {
            case 1:
                // criarGenero();
                break;
            case 2:
                listarGeneros();
                break;
        }
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
        if (listaFilmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            for (Filme filme : listaFilmes) {
                System.out.println("-----------------------");
                System.out.println("ID: " + filme.getId());
                System.out.println("Título: " + filme.getTitulo());
                System.out.println("Data de Lançamento: " + filme.getDataLancamento());
                System.out.println("Gênero: " + filme.getGenero());
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

    public static void deletarGênero() {

    }

    // ----------Aluguel----------------
    public static void realizarAlocacao() {
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

}
