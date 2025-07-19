package entidadesDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import entidades.Genero;

public class GeneroDAO {
    
    public boolean criarGenero(Genero genero) {
        String query = "INSERT INTO genero (tipo_genero) VALUES (?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, genero.getTipo_genero());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

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

    public Genero buscarGeneroPorId(int id) {
        String query = "SELECT * FROM genero WHERE id = ?";
        PreparedStatement ps = null;
        Genero genero = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                genero = new Genero();
                genero.setId(rs.getInt("id"));
                genero.setTipo_genero(rs.getString("tipo_genero"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar gênero: " + e.getMessage());
        }

        return genero;
    }

    public boolean alterarGenero(Genero genero) {
        String query = "UPDATE genero SET tipo_genero = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, genero.getTipo_genero());
            ps.setInt(2, genero.getId());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean excluirGenero(int id) {
        String query = "DELETE FROM genero WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean verificarGeneroExistente(int id) {
        if (buscarGeneroPorId(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
