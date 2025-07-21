package entidadesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import entidades.Acervo;

public class AcervoDAO {

    public boolean inserirNoAcervo(Acervo acervo) {
        String query = "INSERT INTO acervo (filme_id, situacao) VALUES (?, ?)";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(query)) {
            ps.setInt(1, acervo.getFilmeId());
            ps.setString(2, acervo.getSituacao().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir acervo: " + e.getMessage());
            return false;
        }

        return true;
    }

    public Acervo buscarAcervoPorId(int idAcervo) {
        String sql = "SELECT * FROM acervo WHERE idAcervo = ?";
        Acervo acervo = null;

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, idAcervo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                acervo = new Acervo();
                acervo.setIdAcervo(rs.getInt("idAcervo"));
                acervo.setFilmeId(rs.getInt("filme_id"));
                acervo.setSituacao(Acervo.Situacao.valueOf(rs.getString("situacao")));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar acervo por ID: " + e.getMessage());
        }

        return acervo;
    }

    public ArrayList<Acervo> listarAcervos() {
        ArrayList<Acervo> lista = new ArrayList<>();
        String query = "SELECT * FROM acervo";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Acervo acervo = new Acervo();
                acervo.setIdAcervo(rs.getInt("idAcervo"));
                acervo.setFilmeId(rs.getInt("filme_id"));
                acervo.setSituacao(Acervo.Situacao.valueOf(rs.getString("situacao")));

                lista.add(acervo);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar acervos: " + e.getMessage());
        }

        return lista;
    }

    public ArrayList<Acervo> listarAcervosPorSituacao(Acervo.Situacao situacao) {
        ArrayList<Acervo> lista = new ArrayList<>();
        String query = "SELECT * FROM acervo WHERE situacao = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(query)) {
            ps.setString(1, situacao.name());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Acervo acervo = new Acervo();
                acervo.setIdAcervo(rs.getInt("idAcervo"));
                acervo.setFilmeId(rs.getInt("filme_id"));
                acervo.setSituacao(Acervo.Situacao.valueOf(rs.getString("situacao")));

                lista.add(acervo);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar acervos: " + e.getMessage());
        }

        return lista;
    }

    public boolean alterarSituacao(int idAcervo, Acervo.Situacao novaSituacao) {
        String query = "UPDATE acervo SET situacao = ? WHERE idAcervo = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(query)) {
            ps.setString(1, novaSituacao.name());
            ps.setInt(2, idAcervo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar situação do acervo: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deletarAcervo(int idAcervo) {
        String query = "DELETE FROM acervo WHERE idAcervo = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(query)) {
            ps.setInt(1, idAcervo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar acervo: " + e.getMessage());
            return false;
        }

        return true;
    }

    public Acervo buscarProximoAcervoDisponivel(int idFilme) {
        String query = "SELECT * FROM acervo WHERE filme_id = ? AND situacao = 'DISPONIVEL' LIMIT 1";
        Acervo acervo = null;

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(query)) {
            ps.setInt(1, idFilme);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                acervo = new Acervo();
                acervo.setIdAcervo(rs.getInt("idAcervo"));
                acervo.setFilmeId(rs.getInt("filme_id"));
                acervo.setSituacao(Acervo.Situacao.valueOf(rs.getString("situacao")));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar próximo acervo disponível: " + e.getMessage());
        }

        return acervo;
    }
}
