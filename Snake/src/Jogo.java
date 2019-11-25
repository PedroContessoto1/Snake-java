import javax.swing.*;


public class Jogo {

    private Jogo(){
        JFrame frame = new JFrame();
        Panel_do_jogo Panel = new Panel_do_jogo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Panel);
        frame.setTitle("Jogo da cobrinha");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new Jogo();
    }
}
