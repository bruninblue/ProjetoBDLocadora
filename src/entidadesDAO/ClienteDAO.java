package entidadesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexao.Conexao;
import entidades.Cliente;


public class ClienteDAO {
    
    public boolean inserirCliente(Cliente cli){
        String query_insert = "INSERT INTO CLIENTE (CPF, NOME_COMPLETO, NUM_TELEFONE) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query_insert);
            ps.setString(1, cli.getCpf());
            ps.setString(2, cli.getNomeCompleto());
            ps.setString(3, cli.getNumTelefone());

            ps.execute();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

        return true;
    }

    public ArrayList<Cliente> listarClientes(){
        ArrayList<Cliente> listCli = new ArrayList<>();
        String query_select = "SELECT * FROM CLIENTE";

        PreparedStatement ps = null;

        try{
            ps = Conexao.getConexao().prepareStatement(query_select);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Cliente cli = new Cliente();
                cli.setCpf(resultado.getString("cpf"));
                cli.setNomeCompleto(resultado.getString("nome_completo"));
                cli.setNumTelefone(resultado.getString("num_telefone"));

                listCli.add(cli);
            }
            ps.close();
        }catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }

        return listCli;
    }

    public boolean alterarDadosCliente(Cliente novoDado, int opcaoAlterar){
        PreparedStatement ps = null;
        String query_update = "";
        if(opcaoAlterar == 1){
            //Alterar só o nome completo

            query_update = "UPDATE CLIENTE SET NOME_COMPLETO = ? WHERE CPF = ?";

            try {
                ps = Conexao.getConexao().prepareStatement(query_update);
                ps.setString(1, novoDado.getNomeCompleto());
                ps.setString(2, novoDado.getCpf());
                
                ps.executeUpdate();

                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }else if(opcaoAlterar == 2){
            //Alterar só o número de telefone
            query_update = "UPDATE CLIENTE SET NUM_TELEFONE = ? WHERE CPF = ?";

            try {
                ps = Conexao.getConexao().prepareStatement(query_update);
                ps.setString(1, novoDado.getNumTelefone());
                ps.setString(2, novoDado.getCpf());

                ps.execute();

                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }else if(opcaoAlterar == 3){
            //Alterar nome completo e número de telefone
            query_update = "UPDATE CLIENTE SET NOME_COMPLETO = ?, NUM_TELEFONE = ? WHERE CPF = ?";

            try {
                ps = Conexao.getConexao().prepareStatement(query_update);
                ps.setString(1, novoDado.getNomeCompleto());
                ps.setString(2, novoDado.getNumTelefone());
                ps.setString(3, novoDado.getCpf());

                ps.execute();

                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
}