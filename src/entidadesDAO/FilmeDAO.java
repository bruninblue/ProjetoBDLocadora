package entidadesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entidades.Filme;

public class FilmeDAO {

    public boolean criarFilme(Filme filme) {
        String query = "INSERT INTO filme (nome_filme, data_lancamento, genero_filme, valor_filme) VALUES (?,?,?,?)";

        PreparedStatement ps = null;

        try {
            int i = 1;
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(i++, filme.getTitulo());
            ps.setDate(i++, filme.getDataLancamento());
            ps.setInt(i++, filme.getGenero());
            ps.setDouble(i++, filme.getValor());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

        return true;
    }

    public List<Filme> listarFilmes() {
        List<Filme> listaFilmes = new ArrayList<>();
        String query = "SELECT f.*, COUNT(a.idAcervo) AS quantidadeDisponivel FROM filme f LEFT JOIN acervo a on a.filme_id = f.idFilme group by f.idFilme";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Filme filme = new Filme();
                filme.setId(resultado.getInt("idFilme"));
                filme.setTitulo(resultado.getString("nome_filme"));
                filme.setDataLancamento(resultado.getDate("data_lancamento"));
                filme.setGenero(resultado.getInt("genero_filme"));
                filme.setValor(resultado.getFloat("valor_filme"));
                filme.setQuantidadeDisponivel(resultado.getInt("quantidadeDisponivel"));

                listaFilmes.add(filme);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }

        return listaFilmes;
    }

    public List<Filme> listarFilmesDisponiveis() {
         List<Filme> listaFilmes = new ArrayList<>();
        String query = "SELECT f.*, COUNT(a.idAcervo) AS quantidadeDisponivel FROM filme f INNER JOIN acervo a on a.filme_id = f.idFilme WHERE a.situacao = 'DISPONIVEL' group by f.idFilme;";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Filme filme = new Filme();
                filme.setId(resultado.getInt("idFilme"));
                filme.setTitulo(resultado.getString("nome_filme"));
                filme.setDataLancamento(resultado.getDate("data_lancamento"));
                filme.setGenero(resultado.getInt("genero_filme"));
                filme.setValor(resultado.getFloat("valor_filme"));
                filme.setQuantidadeDisponivel(resultado.getInt("quantidadeDisponivel"));

                listaFilmes.add(filme);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }

        return listaFilmes;
    }

    public boolean alterarDadosFilme(Filme filme, int opcaoAlterar) {
        StringBuilder query = new StringBuilder("UPDATE FILME SET ");

        PreparedStatement ps = null;

        switch (opcaoAlterar) {
            case 1:
                query.append("nome_filme = ?");
                break;
            case 2:
                query.append("data_lancamento = ?");
                break;
            case 3:
                query.append("genero_filme = ?");
                break;
            case 4:
                query.append("valor_filme = ?");
                break;
            case 5:
                query.append("nome_filme = ?, data_lancamento = ?, genero_filme = ?, valor_filme = ?");
        }

        query.append(" WHERE idFilme = ?");

        try {
            ps = Conexao.getConexao().prepareStatement(query.toString());

            int i = 1;
            switch (opcaoAlterar) {
                case 1:
                    ps.setString(i++, filme.getTitulo());
                    break;
                case 2:
                    ps.setDate(i++, filme.getDataLancamento());
                    break;
                case 3:
                    ps.setInt(i++, filme.getGenero());
                    break;
                case 4:
                    ps.setFloat(i++, filme.getValor());
                    break;
                case 5:
                    ps.setString(i++, filme.getTitulo());
                    ps.setDate(i++, filme.getDataLancamento());
                    ps.setInt(i++, filme.getGenero());
                    ps.setFloat(i++, filme.getValor());
            }

            ps.setInt(i++, filme.getId());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean excluirFilme(int id) {
        String query = "DELETE FROM FILME WHERE idFilme = ?";

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

    public Filme buscarFilmePorId(int id) {
        String query = "SELECT * FROM FILME WHERE idFilme = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Filme filme = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                filme = new Filme();
                filme.setId(rs.getInt("idFilme"));
                filme.setTitulo(rs.getString("nome_filme"));
                filme.setDataLancamento(rs.getDate("data_lancamento"));
                filme.setGenero(rs.getInt("genero_filme"));
                filme.setValor(rs.getFloat("valor_filme"));
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filme;
    }
}
