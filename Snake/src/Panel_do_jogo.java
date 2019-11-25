import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Panel_do_jogo extends JPanel implements Runnable, KeyListener {

    private static final int SIZE_FRUTA = 10;
    private static int horizontal = 500, vertical = 500;

    private int xCorpo = 10, yCorpo = 10, size = 5;
    private int ticks = 0;
    private boolean DIREITA = true;
    private boolean ESQUERDA = false;
    private boolean CIMA = false;
    private boolean BAIXO = false;
    private Random rand;
    private Corpo_cobra cobra_parametros;
    private Comida comida_parametros;
    private ArrayList<Corpo_cobra> cobra;
    private ArrayList<Comida> fruta;

    private boolean começar_jogo;

    private Thread thread;

    Panel_do_jogo(){
        setFocusable(true);

        setPreferredSize(new Dimension(horizontal,vertical));
        addKeyListener(this);

        cobra = new ArrayList<>();
        fruta = new ArrayList<>();

        rand = new Random();

    }


    private void começar(){
        começar_jogo = true;
        thread = new Thread(this);
        thread.start();
    }

    private void parar() throws InterruptedException {
        começar_jogo = false;
        thread.join();

    }

    public void restart(){

    }

    public void corpo() throws InterruptedException {

        ticks++;
        if (ticks > 999999){
            if (DIREITA) xCorpo++;
            if (ESQUERDA) xCorpo--;
            if (BAIXO) yCorpo++;
            if (CIMA) yCorpo--;
            ticks = 0;

            cobra_parametros = new Corpo_cobra(xCorpo, yCorpo,SIZE_FRUTA);
            cobra.add(cobra_parametros);

            if (cobra.size() > size){
                cobra.remove(0);
            }
        }
        if (fruta.size() == 0){
            int xfruta = rand.nextInt(49);
            int yfruta = rand.nextInt(49);

            comida_parametros = new Comida(xfruta,yfruta, SIZE_FRUTA);
            fruta.add(comida_parametros);
        }
        for (int i = 0; i < fruta.size();i++){
            if (xCorpo == fruta.get(i).getXfruta() && yCorpo == fruta.get(i).getYfruta()){
                fruta.remove(i);
                size += 3;
            }
        }

        if (yCorpo == 50) {
            parar();
        } else if (xCorpo == 50 || xCorpo < 0 || yCorpo < 0) {
            parar();
        }

        for(int i = 0; i < cobra.size() - 1;i++){
            if(cobra.get(i).getXcorpo() == xCorpo && cobra.get(i).getYcorpo() == yCorpo){
                parar();
            }
        }

    }

    public void paint(Graphics g){

        g.clearRect(0,0,horizontal,vertical);
        g.setColor(Color.green);
        g.fillRect(0,0,horizontal,vertical);

        int fontSize = 20;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(Color.BLACK);
        g.drawString("Placar : " + (size - 5) / 3, horizontal/2, 20);

        for (Corpo_cobra corpo_cobra : cobra) {
            corpo_cobra.desenhar(g);
        }
        for (Comida comida : fruta) {
            comida.desenhar(g);
        }
    }

    public void run() {
        while(começar_jogo){
            try {
                corpo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP && !BAIXO){
            CIMA = true;
            DIREITA = false;
            ESQUERDA = false;
        }
        if(key == KeyEvent.VK_RIGHT && !ESQUERDA){
            DIREITA = true;
            CIMA = false;
            BAIXO = false;

        }
        if(key == KeyEvent.VK_LEFT && !DIREITA){
            ESQUERDA = true;
            CIMA = false;
            BAIXO = false;
        }
        if(key == KeyEvent.VK_DOWN && !CIMA){
            BAIXO = true;
            DIREITA = false;
            ESQUERDA = false;
        }
        if(key == KeyEvent.VK_SPACE){
            começar();
        }
        if(key == KeyEvent.VK_U){
            size+= 3;
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


}
