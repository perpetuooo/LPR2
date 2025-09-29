// Pedro H Perpétuo - CB3021688
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AlunoForm().setVisible(true);
            }
        });
    }
}
