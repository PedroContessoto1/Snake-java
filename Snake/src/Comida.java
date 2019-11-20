import java.awt.*;

public class Comida extends Panel_do_jogo{
    private int xfruta, yfruta, horizontal, vertical;

    public Comida(int xfruta,int yfruta,int size_fruta){
        this.xfruta = xfruta;
        this.yfruta = yfruta;
        horizontal = size_fruta;
        vertical = size_fruta;
    }

   public void corpo(){

   }

    public void desenhar(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(xfruta * horizontal ,yfruta * vertical,horizontal,vertical);
    }

    public int getXfruta() {
        return xfruta;
    }

    public void setXfruta(int xfruta){
        this.xfruta = xfruta;
    }

    public int getYfruta() {
        return yfruta;
    }

    public void setYfruta(int yfruta){
        this.yfruta = yfruta;
    }
}
