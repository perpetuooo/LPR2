//  CBTLPR2 TP03 
// Pedro H Perpétuo - CB3021688

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calc extends JFrame implements ActionListener {
    JTextField t;
    String op = "";
    double a = 0;
    boolean clr = true;

    public Calc() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        t = new JTextField("0");
        t.setHorizontalAlignment(SwingConstants.RIGHT);
        t.setEditable(false);
        t.setFont(new Font("SansSerif", Font.PLAIN, 24));
        add(t, BorderLayout.NORTH);

        JPanel p = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] keys = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+","C"," "," "," "};

        for (String s : keys) {
            JButton b = new JButton(s);
            b.setFont(new Font("SansSerif", Font.BOLD, 20));
            
            if (s.equals(" ")) {
                b.setEnabled(false);
            } else b.addActionListener(this);
            p.add(b);
        }

        add(p, BorderLayout.CENTER);
        setSize(260, 330);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if ("0123456789.".contains(s)) {
            if (clr || t.getText().equals("0")) {
                if (s.equals(".")) t.setText("0.");
                else t.setText(s);
                clr = false;

            } else {
                if (s.equals(".") && t.getText().contains(".")) return;
                t.setText(t.getText() + s);
            }

            return;
        }
        if (s.equals("C")) {
            a = 0;
            op = "";
            t.setText("0");
            clr = true;

            return;
        }
        if ("+-*/".contains(s)) {
            try {
                a = Double.parseDouble(t.getText());

                op = s;
                clr = true;
            } catch (Exception ex) {
                t.setText("Erro");

                a = 0;
                op = "";
                clr = true;
            } finally {
                if (t.getText().isEmpty()) t.setText("0");
            }

            return;
        }
        if (s.equals("=")) {
            try {
                double b = Double.parseDouble(t.getText());
                double r = 0;
                if (op.equals("+")) r = a + b;
                else if (op.equals("-")) r = a - b;
                else if (op.equals("*")) r = a * b;

                else if (op.equals("/")) {
                    if (b == 0) throw new ArithmeticException();
                    
                    r = a / b;

                } else r = b;
                if (Math.floor(r) == r){
                  t.setText(String.valueOf((long) r));  

                } else t.setText(String.valueOf(r));

                a = r;
                op = "";
                clr = true;
            } catch (Exception ex) {
                t.setText("Erro");

                a = 0;
                op = "";
                clr = true;
            } finally {
                if (t.getText().isEmpty()) t.setText("0");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeyseLater(() -> new Calc().setVisible(true));
    }
}
