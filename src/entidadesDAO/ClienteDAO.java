package entidadesDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import entidades.Cliente;


public class ClienteDAO {
    
    public void inserirCliente(Cliente cli){
        String query_insert = "INSERT INTO CLIENTE (TIPO_GENERO) VALUES (?)";

        PreparedStatement ps = null;

        // try {
        //     ps = Conexao.getConexao().prepareStatement(sql_query_insert);
        //     ps.setString(2, gen.getTipo());

        //     ps.execute();

        //     ps.close();

        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
    }
}
