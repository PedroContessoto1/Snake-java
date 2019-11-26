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
    int xfrutaAmarela;
    int yfrutaAmarela;

    private Random rand;

    private Corpo_cobra cobra_parametros;
    private FrutaVermelha frutaVermelha_parametros;
    private FrutaAmarela frutaAmarela_parametros;

    private ArrayList<Corpo_cobra> cobra;
    private ArrayList<FrutaVermelha> frutaVermelha;
    private ArrayList<FrutaAmarela> frutaAmarela;

    private boolean começar_jogo;

    private Thread thread;

    Panel_do_jogo(){
        setFocusable(true);

        setPreferredSize(new Dimension(horizontal,vertical));
        addKeyListener(this);

        cobra = new ArrayList<>();
        frutaVermelha = new ArrayList<>();
        frutaAmarela = new ArrayList<>();

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
        //  bertho 999999
        //  pedro 250000
        ticks++;
        if (ticks > 699999){
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
        if (frutaVermelha.size() == 0){
            int xfruta = rand.nextInt(49);
            int yfruta = rand.nextInt(49);

            frutaVermelha_parametros = new FrutaVermelha(xfruta,yfruta, SIZE_FRUTA);
            frutaVermelha.add(frutaVermelha_parametros);
        }

        if (frutaAmarela.size() == 0){
            xfrutaAmarela = rand.nextInt(49);
            yfrutaAmarela = rand.nextInt(49);

            frutaAmarela_parametros = new FrutaAmarela(xfrutaAmarela,yfrutaAmarela, SIZE_FRUTA);
            frutaAmarela.add(frutaAmarela_parametros);

        }




        for (int i = 0; i < frutaVermelha.size(); i++){
            if (xCorpo == frutaVermelha.get(i).getXfruta() && yCorpo == frutaVermelha.get(i).getYfruta()){
                frutaVermelha.remove(i);
                size += 3;
            }
        }

        for (int i = 0; i < frutaAmarela.size(); i++){
            if (xCorpo == frutaAmarela.get(i).getXfruta() && yCorpo == frutaAmarela.get(i).getYfruta()){
                frutaAmarela.remove(i);
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
        for (int i = 0; i < cobra.size(); i++){
            cobra.get(i).desenhar(g);
        }
        for (int k = 0; k < frutaVermelha.size(); k++){
            frutaVermelha.get(k).desenhar(g);
        }
        for (int k = 0; k < frutaAmarela.size(); k++){
            frutaAmarela.get(k).desenhar(g);
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
