import java.util.ArrayList;
import java.util.Scanner;
import utils.Utils;
import entidades.Cliente;
import entidadesDAO.ClienteDAO;
public class App {
    
    public static Scanner leia = new Scanner(System.in);
    
    public static void main (String[] args){
        menuPrincipal();
    }

    public static void menuPrincipal(){
        int opcao = 0;

        while(opcao != 4){
            System.out.println("Bem-vindo a locadora de filmes, qual a sua categoria?");
            System.out.println("1 - Cliente");
            System.out.println("2 - Filmes");
            System.out.println("3 - Aluguel");
            System.out.println("4 - Sair");
            
            System.out.println("Digite uma opção: ");
            opcao = leia.nextInt();
            
            switch(opcao){
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuFilmes();
                    break;
                case 3:
                    menuAluguel();
                    break;
            }
        }
    }
    public static void menuCliente(){
        
        int opcao = 0;

        do{
            System.out.println("Menu de Cliente");
            System.out.println("1 - Cadastrar novo cliente");
            System.out.println("2 - Listar clientes cadastrados");
            System.out.println("3 - Excluir cliente");
            System.out.println("4 - Alterar dados do cliente");
            System.out.println("5 - Voltar ao menu principal");

            System.out.println("Digite uma opção: ");
            opcao = leia.nextInt();

            switch(opcao){
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
        }while(opcao < 1 || opcao > 5);
        
    }
    public static void menuFilmes(){
        
    }

    public static void menuAluguel(){

    }
    //-----------Cliente--------------
    public static void inserirCliente(){
        //Adicionar verificações
        System.out.println("Digite seu CPF: ");
        leia.nextLine(); //limpeza buffer;
        String cpf = leia.nextLine();
        
        System.out.println("Digite seu nome completo: ");
        String nome_completo = leia.nextLine();
        
        System.out.println("Digite seu número de telefone: ");
        String telefone = leia.nextLine();

        Cliente clienteNovo = new Cliente();
        clienteNovo.setCpf(cpf);
        clienteNovo.setNomeCompleto(nome_completo);
        clienteNovo.setNumTelefone(telefone);
        
        if(new ClienteDAO().inserirCliente(clienteNovo)){
            System.out.println("Cadastrado com sucesso.");
        }else{
            System.out.println("Não foi possível cadastrar o cliente.");
        }

    }
    public static void listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes = new ClienteDAO().listarClientes();

        for(Cliente cli : clientes){
            System.out.println("--------------------------------");
            System.out.println("Nome: " + cli.getNomeCompleto());
            System.out.println("CPF: " + cli.getCpf());
            System.out.println("Telefone:  " + cli.getNumTelefone());
        }

    }
    public static void alterarCliente(){
        System.out.println("Qual informação deseja altera?");
        System.out.println("1 - Nome completo");
        System.out.println("2 - Número de telefone");
        System.out.println("3 - Os dois dados");
        int opcao = leia.nextInt();

        System.out.println("Qual o cpf do cliente que deseja alterar?");
        leia.nextLine();//limpar buffer
        String cpf = leia.nextLine();

        if(Utils.verificarExistenciaCliente(cpf) == 1){
            if(opcao == 1){
                System.out.println("Digite o novo nome completo: ");
                String novoNome = leia.nextLine();
                Cliente cli = new Cliente();
                cli.setCpf(cpf);
                cli.setNomeCompleto(novoNome);

                if(new ClienteDAO().alterarDadosCliente(cli, opcao)){
                    System.out.println("Alteração feita com sucesso");
                }else{
                    System.out.println("Não foi possível fazer a alteração");
                }
                
            }
        }

        
        
    }
    public static void excluirCliente(){
        
    }

    //----------Filmes-----------------
    public static void inserirFilme(){
        
    }
    public static void listarFilme(){
        
    }
    public static void alterarFilme(){
        
    }
    public static void excluirFilme(){
        
    }
    
    
}
