package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/locadora_filme"; //URL do banco
    private static final String user = "root"; //usuário
    private static final String password = "12345678";//"xman.1700"; //senha

    private static Connection conexao;

    public static Connection getConexao(){
        try {
            if(conexao == null){
                conexao = DriverManager.getConnection(url, user, password);
                return conexao;
            }else{
                return conexao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}