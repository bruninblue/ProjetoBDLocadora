package entidadesDAO;

import entidades.ItemLocacao;
import conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;

public class ItemLocacaoDAO {

    public boolean inserirItemLocacao(ItemLocacao item) {
        String sql = "INSERT INTO itemlocacao (idLocacao, idAcervo) VALUES (?, ?)";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, item.getIdLocacao());
            ps.setInt(2, item.getIdAcervo());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<ItemLocacao> listarItensPorLocacao(int idLocacao) {
        ArrayList<ItemLocacao> itens = new ArrayList<>();
        String sql = "SELECT * FROM itemlocacao WHERE idLocacao = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, idLocacao);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemLocacao item = new ItemLocacao();
                item.setIdItemLocacao(rs.getInt("idItemLocacao"));
                item.setIdLocacao(rs.getInt("idLocacao"));
                item.setIdAcervo(rs.getInt("idAcervo"));
                itens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }

    public boolean excluirItensPorLocacao(int idLocacao) {
        String sql = "DELETE FROM itemlocacao WHERE idLocacao = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, idLocacao);
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
