import java.awt.*;

public class Corpo_cobra extends Panel_do_jogo {

    private int xcorpo, ycorpo, horizontal, vertical;

    public Corpo_cobra(int xcorpo,int ycorpo,int size_cobra){
        this.xcorpo = xcorpo;
        this.ycorpo = ycorpo;
        horizontal = size_cobra;
        vertical = size_cobra;
    }

    public void corpo(){

    }

    public void desenhar(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(xcorpo * horizontal,ycorpo * vertical,horizontal,vertical);
    }

    public int getXcorpo() {
        return xcorpo;
    }

    public void setXcorpo(int xcorpo){
        this.xcorpo = xcorpo;
    }

    public int getYcorpo() {
        return ycorpo;
    }

    public void setYcorpo(int ycorpo){
        this.ycorpo = ycorpo;
    }
}
