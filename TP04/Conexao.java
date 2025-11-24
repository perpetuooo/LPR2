import java.sql.*;

public class Conexao {
    private static final String URL = System.getenv();
    private static final String USER = System.getenv();
    private static final String PASS = System.getenv();
    
    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}