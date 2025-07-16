import java.util.Scanner;
public class App {
    
    public static Scanner leia = new Scanner(System.in);
    
    public static void main (String[] args){
        menuPrincipal();
    }
    
    public static void menuPrincipal(){
        while (true) {
            System.out.println("Bem-vindo a locadora de filmes, qual a sua categoria?");
            System.out.println("1 - Cliente");
            System.out.println("2 - Filmes");
            System.out.println("3 - Aluguel");
            System.out.println("4 - Sair");
            
            System.out.println("Digite uma opção: ");
            int opcao = leia.nextInt();
            System.out.println(opcao);
            
            switch(opcao){
                case 1:
                    menuCliente();
                case 2:
                    menuFilmes();
                case 3:
                    menuAluguel();
                case 4:
                    break;
            }

            if(opcao == 4){
                break;
            }
        } 
    }
    public static void menuCliente(){
        //do while
        System.out.println("Menu de Cliente");
        System.out.println("1 - Cadastrar novo cliente");
        System.out.println("2 - Listar clientes cadastrados");
        System.out.println("3 - Excluir cliente");
        System.out.println("4 - Alterar dados do cliente");
        System.out.println("5 - Voltar ao menu principal");

        System.out.println("Digite uma opção: ");
        int opcao = leia.nextInt();
        System.out.println(opcao);
        
        switch(opcao){
            case 1:
                inserirCliente();
            case 2:
                listarClientes();
            case 3:
                alterarCliente();
            case 4:
                excluirCliente();
            case 5:
                menuPrincipal();
        }
    }
    public static void menuFilmes(){
        
    }

    public static void menuAluguel(){

    }
    //-----------Cliente--------------
    public static void inserirCliente(){
        
    }
    public static void listarClientes(){
        
    }
    public static void alterarCliente(){
        
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
