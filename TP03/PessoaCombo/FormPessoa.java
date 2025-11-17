//  CBTLPR2 TP03 
// Pedro H Perpétuo - CB3021688

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormPessoa extends JFrame implements ActionListener {
    JTextField fNum;
    JTextField fNome;
    JComboBox<String> cSexo;
    JTextField fIdade;
    JButton bOk;
    JButton bMostrar;
    Pessoa UmaPessoa = new Pessoa();

    public FormPessoa() {
        setTitle("FormPessoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel lNum = new JLabel("Numero");
        JLabel lNome = new JLabel("Nome");
        JLabel lSexo = new JLabel("Sexo");
        JLabel lIdade = new JLabel("Idade");

        fNum = new JTextField("0", 10);
        fNum.setEditable(false);
        fNome = new JTextField(15);
        cSexo = new JComboBox<>(new String[]{"M","F"});
        fIdade = new JTextField(5);

        c.gridx = 0; c.gridy = 0; form.add(lNum, c);
        c.gridx = 1; c.gridy = 0; form.add(fNum, c);
        c.gridx = 0; c.gridy = 1; form.add(lNome, c);
        c.gridx = 1; c.gridy = 1; form.add(fNome, c);
        c.gridx = 0; c.gridy = 2; form.add(lSexo, c);
        c.gridx = 1; c.gridy = 2; form.add(cSexo, c);
        c.gridx = 0; c.gridy = 3; form.add(lIdade, c);
        c.gridx = 1; c.gridy = 3; form.add(fIdade, c);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bOk = new JButton("OK");
        bMostrar = new JButton("Mostrar");
        bOk.addActionListener(this);
        bMostrar.addActionListener(this);
        botoes.add(bOk);
        botoes.add(bMostrar);

        add(form, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bOk) {
            try {
                String nome = fNome.getText().trim();
                String sx = String.valueOf(cSexo.getSelectedItem());
                String idt = fIdade.getText().trim();

                if (nome.isEmpty() || idt.isEmpty()) throw new Exception();
                if (!(sx.equals("M") || sx.equals("F"))) throw new Exception();

                int idade = Integer.parseInt(idt);
                UmaPessoa.setNome(nome);
                UmaPessoa.setSexo(sx.charAt(0));
                UmaPessoa.setIdade(idade);
                UmaPessoa.setKp();

                fNum.setText(String.valueOf(Pessoa.getKp()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Dados invalidos");

            } finally {
                if (fNum.getText().isEmpty()) fNum.setText(String.valueOf(Pessoa.getKp()));
            }
        } else if (e.getSource() == bMostrar) {
            try {
                String txt = "Numero: " + Pessoa.getKp() + "\n" +
                        "Nome: " + (UmaPessoa.getNome()==null?"":UmaPessoa.getNome()) + "\n" +
                        "Sexo: " + (UmaPessoa.getSexo()==0?' ':UmaPessoa.getSexo()) + "\n" +
                        "Idade: " + UmaPessoa.getIdade();
                JOptionPane.showMessageDialog(this, txt);
                fNum.setText(String.valueOf(Pessoa.getKp()));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro");
                
            } finally {
                if (fNum.getText().isEmpty()) fNum.setText(String.valueOf(Pessoa.getKp()));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPessoa().setVisible(true));
    }
}
