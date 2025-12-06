import java.util.*;
import java.sql.*;

public class Paciente {
    private int id;
    private String nome;
    private int idade;
    private float peso;
    private float altura;

    public Paciente(int id, String nome, int idade, float peso, float altura) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public float getPeso() { return peso; }
    public float getAltura() { return altura; }
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "ID: " + id + " - Nome: " + nome + " - Idade: " + idade + " - Peso: " + peso + " - Altura: " + altura;
    }
    public static class DAO {
        public void salvar(Paciente p) throws SQLException {
            String sql = "INSERT INTO tbPacientes (nome, idade, peso, altura) VALUES (?, ?, ?, ?)";
            try (Connection conn = Conexao.get();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, p.getNome());
                ps.setInt(2, p.getIdade());
                ps.setFloat(3, p.getPeso());
                ps.setFloat(4, p.getAltura());
                ps.executeUpdate();
                
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        p.setId(rs.getInt(1));
                    }
                }
            }
        }

        public List<Paciente> listarTodos() {
            List<Paciente> lista = new ArrayList<>();
            String sql = "SELECT * FROM tbPacientes ORDER BY nome";
            try (Connection conn = Conexao.get();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getFloat("peso"),
                        rs.getFloat("altura")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return lista;
        }

        public List<Paciente> buscarPorNome(String termo) {
            List<Paciente> lista = new ArrayList<>();
            String sql = "SELECT * FROM tbPacientes WHERE LOWER(nome) LIKE ? ORDER BY nome";
            try (Connection conn = Conexao.get();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "%" + termo.toLowerCase() + "%");
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lista.add(new Paciente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getFloat("peso"),
                            rs.getFloat("altura")
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

