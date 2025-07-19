package entidadesDAO;

import entidades.Aluguel;
import conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;

public class AluguelDAO {

    public boolean inserirAluguel(Aluguel aluguel) {
        String sql = "INSERT INTO locacao (data_alugado, data_devolvido, valor_final, multa, pendente, cliente_cpf) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, aluguel.getDataAluguel());
            ps.setDate(2, aluguel.getDataDevolucao());
            ps.setFloat(3, aluguel.getValorPagar());
            ps.setFloat(4, aluguel.getMulta());
            ps.setInt(5, aluguel.getPendente());
            ps.setString(6, String.valueOf(aluguel.getClienteCpf())); // cpf é String no banco

            ps.executeUpdate();

            // Recuperar o ID gerado
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                aluguel.setId(rs.getInt(1));
            }

            ps.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Aluguel> listarAlugueis() {
        ArrayList<Aluguel> lista = new ArrayList<>();
        String sql = "SELECT * FROM locacao";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aluguel aluguel = new Aluguel();
                aluguel.setId(rs.getInt("id"));
                aluguel.setDataAluguel(rs.getDate("data_alugado"));
                aluguel.setDataDevolucao(rs.getDate("data_devolvido"));
                aluguel.setValorPagar(rs.getFloat("valor_final"));
                aluguel.setMulta(rs.getFloat("multa"));
                aluguel.setPendente(rs.getInt("pendente"));
                aluguel.setClienteCpf(rs.getString("cliente_cpf")); // convertendo CPF para int

                lista.add(aluguel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método útil para atualizar o valor final ou marcar como finalizado
    public boolean atualizarValorFinalEMulta(int idAluguel, float valorFinal, float multa, int pendente) {
        String sql = "UPDATE locacao SET valor_final = ?, multa = ?, pendente = ? WHERE id = ?";
        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setFloat(1, valorFinal);
            ps.setFloat(2, multa);
            ps.setInt(3, pendente);
            ps.setInt(4, idAluguel);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
