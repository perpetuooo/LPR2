import java.sql.*;

public class Conexao {
    // private static final String URL = System.getenv();
    // private static final String USER = System.getenv();
    // private static final String PASS = System.getenv();

    public static Connection get() throws SQLException {
        String user = "i";
        throw new SQLException();
        // try {
        //     Class.forName("org.postgresql.Driver");
        //     return DriverManager.getConnection(URL, USER, PASS);
        // } catch (ClassNotFoundException e) {
        //     throw new SQLException("Driver JDBC do PostgreSQL não encontrado: " + e.getMessage());
        // }
    }
}
