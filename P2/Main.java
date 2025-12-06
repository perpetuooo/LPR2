import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {
    private final JTextField campoNome = new JTextField();
    private final JTextField campoIdade = new JTextField();
    private final JTextField campoPeso = new JTextField();
    private final JTextField campoAltura = new JTextField();
    
    private final JButton botaoIncluir = new JButton("Incluir");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoApresentar = new JButton("Dados");
    private final JButton botaoPesquisar = new JButton("Pesquisar");
    private final JButton botaoCreditos = new JButton("Creditos");
    private final JButton botaoSair = new JButton("Sair");

    private final Paciente.DAO dao = new Paciente.DAO();

    public Main() {
        setTitle("Cadastro de Pacientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel centro = new JPanel(new GridLayout(4, 2, 5, 5));
        centro.add(new JLabel("Nome:"));
        centro.add(campoNome);
        centro.add(new JLabel("Idade:"));
        centro.add(campoIdade);
        centro.add(new JLabel("Peso:"));
        centro.add(campoPeso);
        centro.add(new JLabel("Altura:"));
        centro.add(campoAltura);
        add(centro, BorderLayout.CENTER);

        JPanel base = new JPanel(new GridLayout(2, 3, 5, 5));
        base.add(botaoIncluir);
        base.add(botaoLimpar);
        base.add(botaoApresentar);
        base.add(botaoPesquisar);
        base.add(botaoCreditos);
        base.add(botaoSair);
        add(base, BorderLayout.SOUTH);

        botaoIncluir.addActionListener(e -> incluir());
        botaoLimpar.addActionListener(e -> limpar());
        botaoApresentar.addActionListener(e -> apresentarDados());
        botaoPesquisar.addActionListener(e -> pesquisar());
        botaoCreditos.addActionListener(e -> mostrarCreditos());
        botaoSair.addActionListener(e -> System.exit(0));

        setPreferredSize(new Dimension(400, 500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void incluir() {
        try {
            String nome = campoNome.getText();
            int idade = Integer.parseInt(campoIdade.getText());
            float peso = Float.parseFloat(campoPeso.getText());
            float altura = Float.parseFloat(campoAltura.getText());

            if (nome.isEmpty()) throw new Exception("Nome é obrigatorio");

            Paciente p = new Paciente(0, nome, idade, peso, altura);
            dao.salvar(p);

            JOptionPane.showMessageDialog(this, "Paciente incluido com ID: " + p.getId());
            limpar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro nos dados: Verifique Idade, Peso ou Altura.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao incluir: " + ex.getMessage());
        }
    }

    private void limpar() {
        campoNome.setText("");
        campoIdade.setText("");
        campoPeso.setText("");
        campoAltura.setText("");
    }

    private void apresentarDados() {
        List<Paciente> lista = dao.listarTodos();
        mostrarLista(lista, "Lista de Pacientes");
    }

    private void pesquisar() {
        String termo = JOptionPane.showInputDialog(this, "Digite o nome para pesquisar:");
        if (termo != null && !termo.trim().isEmpty()) {
            List<Paciente> lista = dao.buscarPorNome(termo);
            mostrarLista(lista, "Resultados da Pesquisa");
        }
    }

    private void mostrarLista(List<Paciente> lista, String titulo) {
        StringBuilder sb = new StringBuilder();
        if (lista.isEmpty()) {
            sb.append("Nenhum registro encontrado.");
        } else {
            for (Paciente p : lista) {
                sb.append(p.toString()).append("\n");
            }
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarCreditos() {
        JOptionPane.showMessageDialog(this, "Pedro H Perpetuo - CB3021688", "Creditos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new Main();
    }
}
