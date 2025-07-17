package entidadesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Client;

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
        }catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }

        return listCli;
    }
}
