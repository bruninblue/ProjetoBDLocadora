package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;

import conexao.Conexao;

public class Utils {
    public static int verificarExistenciaCliente(String cpf) {
        int verificador = 0;
        String query_select = "SELECT * FROM CLIENTE WHERE CPF=?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(query_select);
            ps.setString(1, cpf);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                verificador++;
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }

        return verificador;
    }

    public static Date somarDias(int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return new Date(calendar.getTimeInMillis());
    }
}
