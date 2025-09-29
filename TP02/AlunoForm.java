import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AlunoForm extends JFrame {
    private JTextField nome = new JTextField(50);
    private JTextField idade = new JTextField(50);
    private JTextField endereco = new JTextField(50);

    private JButton btnOk = new JButton("Ok");
    private JButton btnLimpar = new JButton("Limpar");
    private JButton btnMostrar = new JButton("Mostrar");
    private JButton btnSair = new JButton("Sair");

    private List<Aluno> alunos = new ArrayList<>();

    public AlunoForm() {
        setTitle("Alunos");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(3, 2, 10, 10));
        top.add(new JLabel("Nome:"));
        top.add(nome);
        top.add(new JLabel("Idade:"));
        top.add(idade);
        top.add(new JLabel("Endereco:"));
        top.add(endereco);

        JPanel bottom = new JPanel(new GridLayout(1, 4, 10, 0));
        bottom.add(btnOk);
        bottom.add(btnLimpar);
        bottom.add(btnMostrar);
        bottom.add(btnSair);

        add(top, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setLocationRelativeTo(null);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Aluno a = new Aluno();

                int idadeForm = 0;
                try { idadeForm = Integer.parseInt(idade.getText()); } catch (Exception ex) {}
                
                a.setIdade(idadeForm);
                a.setNome(nome.getText());
                a.setEndereco(endereco.getText());
                a.setUuid(UUID.randomUUID());
                alunos.add(a);
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nome.setText("");
                idade.setText("");
                endereco.setText("");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Resultado\n\n");

                for (Aluno a : alunos) {
                    sb.append("Id: ").append(a.getUuid()).append(" Nome: ").append(a.getNome()).append("\n");
                }

                if (sb.toString().trim().equals("Resultado")) {
                    JOptionPane.showMessageDialog(AlunoForm.this, "Nenhum aluno cadastrado");
                    return;
                }

                JOptionPane.showMessageDialog(AlunoForm.this, sb.toString());
            }
        });

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
