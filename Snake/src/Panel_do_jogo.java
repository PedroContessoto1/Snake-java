import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Panel_do_jogo extends JPanel implements Runnable, KeyListener {

    public static int horizontal = 1000, vertical = 1000;

    private int xcorpo = 10, ycorpo = 10, size = 5;

    private int ticks = 0;

    private boolean direita = true,esquerda = false,cima = false,baixo = false;

    private Random rand;

    private Corpo_cobra cobra_parametros;
    private Comida comida_parametros;

    private ArrayList<Corpo_cobra> cobra;
    private ArrayList<Comida> fruta;

    private boolean começar_jogo;

    private Thread thread;

    public Panel_do_jogo(){
        setFocusable(true);

        setPreferredSize(new Dimension(horizontal,vertical));
        addKeyListener(this);

        cobra = new ArrayList<Corpo_cobra>();
        fruta = new ArrayList<Comida>();

        rand = new Random();

    }


    public void começar(){
        começar_jogo = true;
        thread = new Thread(this);
        thread.start();
    }

    public void parar() throws InterruptedException {
        começar_jogo = false;
        thread.join();

    }

    public void restart(){

    }

    public void corpo() throws InterruptedException {

        ticks++;
        if (ticks > 250000){
            if (direita) xcorpo++;
            if (esquerda) xcorpo--;
            if (baixo) ycorpo++;
            if (cima) ycorpo--;
            ticks = 0;

            cobra_parametros = new Corpo_cobra(xcorpo,ycorpo,20);
            cobra.add(cobra_parametros);

            if (cobra.size() > size){
                cobra.remove(0);
            }
        }
        if (fruta.size() == 0){
            int xfruta = rand.nextInt(49);
            int yfruta = rand.nextInt(49);

            comida_parametros = new Comida(xfruta,yfruta,20);
            fruta.add(comida_parametros);
        }
        for (int i = 0; i < fruta.size();i++){
            if (xcorpo == fruta.get(i).getXfruta() && ycorpo == fruta.get(i).getYfruta()){
                fruta.remove(i);
                size += 3;
            }
        }

        if (xcorpo == 50 || ycorpo == 50 || xcorpo < 0 || ycorpo < 0){
            parar();
        }

        for(int i = 0; i < cobra.size() - 1;i++){
            if(cobra.get(i).getXcorpo() == xcorpo && cobra.get(i).getYcorpo() == ycorpo){
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
        g.drawString("Placar : " + String.valueOf((size - 5)/3), horizontal/2, 20);

        for(int i = 0 ; i < cobra.size();i++){
            cobra.get(i).desenhar(g);
        }
        for(int i = 0 ; i < fruta.size();i++){
            fruta.get(i).desenhar(g);
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
        if(key == KeyEvent.VK_UP && !baixo){
            cima = true;
            direita = false;
            esquerda = false;
        }
        if(key == KeyEvent.VK_RIGHT && !esquerda){
            direita = true;
            cima = false;
            baixo = false;

        }
        if(key == KeyEvent.VK_LEFT && !direita){
            esquerda = true;
            cima = false;
            baixo = false;
        }
        if(key == KeyEvent.VK_DOWN && !cima){
            baixo = true;
            direita = false;
            esquerda = false;
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
