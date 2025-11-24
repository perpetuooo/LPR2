import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class Main extends JFrame {
    private final JTextField campoBusca = new JTextField(25);
    private final JTextField campoNome = new JTextField();
    private final JTextField campoSalario = new JTextField();
    private final JTextField campoCargo = new JTextField();

    private final JButton botaoAnterior = new JButton("Anterior");
    private final JButton botaoPesquisar = new JButton("Pesquisar");
    private final JButton botaoProximo = new JButton("Proximo");

    private final Funcionario.DAO dao = new Funcionario.DAO();
    private java.util.List<Funcionario> resultados = new java.util.ArrayList<>();
    private int indice = -1;

    public Main() {
        setTitle("TP 04");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("Nome:"));
        topo.add(campoBusca);
        topo.add(botaoPesquisar);
        add(topo,BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(3,2,4,4));
        centro.add(new JLabel("Nome:"));
        centro.add(campoNome);
        centro.add(new JLabel("Salario:"));
        centro.add(campoSalario);
        centro.add(new JLabel("Cargo:"));
        centro.add(campoCargo);
        add(centro,BorderLayout.CENTER);

        JPanel base = new JPanel(new GridLayout(1,2));
        base.add(botaoAnterior);
        base.add(botaoProximo);
        add(base,BorderLayout.SOUTH);
        botaoPesquisar.addActionListener(e -> pesquisar());
        botaoAnterior.addActionListener(e -> navegar(-1));
        botaoProximo.addActionListener(e -> navegar(1));
        pack();
        setLocationRelativeTo(null);
    }

    private void pesquisar() {
        resultados = dao.buscarPorNome(campoBusca.getText().trim());

        if (resultados.isEmpty()) { 
            indice=-1;
            limpar();
            return; 
        }

        indice = 0;
        mostrar();
    }

    private void navegar(int passo) {
        if (indice<0) {
            return;
        }

        int novo = indice + passo;
        if (novo<0 || novo>=resultados.size())  {
            return;
        }

        indice = novo;
        mostrar();
    }

    private void mostrar() {
        Funcionario f = resultados.get(indice);
        campoNome.setText(f.getNome());
        campoSalario.setText(String.format(Locale.US,"%.2f",f.getSalario()));
        campoCargo.setText(f.getCargo());
    }

    private void limpar() {
        campoNome.setText("");
        campoSalario.setText("");
        campoCargo.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}