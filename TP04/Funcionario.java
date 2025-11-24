import java.util.*;
import java.sql.*;

public class Funcionario {
    private final int cod;
    private final String nome;
    private final double salario;
    private final int codCargo;
    private final String cargo;

    public Funcionario(int cod,String nome,double salario,int codCargo,String cargo){
        this.cod=cod;this.nome=nome;this.salario=salario;this.codCargo=codCargo;this.cargo=cargo;
    }
    
    public int getCod(){return cod;}
    public String getNome(){return nome;}
    public double getSalario(){return salario;}
    public int getCodCargo(){return codCargo;}
    public String getCargo(){return cargo;}

    public static class DAO {
        public List<Funcionario> buscarPorNome(String termo) {
            List<Funcionario> lista = new ArrayList<>();
            String sql = "SELECT f.cod_func, f.nome_func, f.sal_func, f.cod_cargo, c.ds_cargo " +
                         "FROM tbFuncionarios f " +
                         "LEFT JOIN tbCargos c ON c.cod_cargo = f.cod_cargo " +
                         "WHERE LOWER(f.nome_func) LIKE ? " +
                         "ORDER BY f.nome_func";
            try (Connection cn = Conexao.get(); 
                 PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, "%" + (termo == null ? "" : termo.trim().toLowerCase()) + "%");
                
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lista.add(new Funcionario(
                            rs.getInt(1),
                            rs.getString(2).trim(),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getString(5) != null ? rs.getString(5).trim() : ""
                        ));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return lista;
        }
    }
}