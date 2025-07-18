package entidadesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import entidades.Genero;

public class GeneroDAO {
    
    public ArrayList<Genero> listarGeneros(){
        ArrayList<Genero> listGenero = new ArrayList<>();
        String query_select = "SELECT * FROM GENERO";
        PreparedStatement ps = null;

        try{
            ps = Conexao.getConexao().prepareStatement(query_select);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Genero gen = new Genero();
                gen.setId(rs.getInt("id"));
                gen.setTipo_genero(rs.getString("tipo_genero"));

                listGenero.add(gen);
            }
            ps.close();

        }catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }

        return listGenero;
    }
}
