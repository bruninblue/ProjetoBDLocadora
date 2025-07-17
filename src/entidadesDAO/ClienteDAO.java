package entidadesDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
