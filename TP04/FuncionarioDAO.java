import java.util.*;

public class FuncionarioDAO {
    public List<Funcionario> buscarPorNome(String termo) {
        String sql = "SELECT f.cod_func, f.nome_func, f.sal_func, f.cod_cargo, c.ds_cargo " +
                     "FROM tbFuncionarios f " +
                     "LEFT JOIN tbCargos c ON c.cod_cargo = f.cod_cargo " +
                     "WHERE LOWER(f.nome_func) LIKE ? " +
                     "ORDER BY f.nome_func";
        String param = "%" + (termo == null ? "" : termo.trim().toLowerCase()) + "%";
        return SqlServerDatabase.executeQuery(sql, param);
    }
}