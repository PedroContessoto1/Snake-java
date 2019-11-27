import java.awt.*;

public class FrutaVermelha extends ItemJogo {
    private int xfruta, yfruta, horizontal, vertical;

    FrutaVermelha(int xfruta, int yfruta, int size_fruta){
        this.xfruta = xfruta;
        this.yfruta = yfruta;
        horizontal = size_fruta;
        vertical = size_fruta;
    }

    public void desenhar(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(xfruta * horizontal ,yfruta * vertical,horizontal,vertical);
    }

    public void mover() {
        yfruta+=1;
        if (yfruta == 50){
            yfruta =0;
        }

    }

    int getXfruta() {
        return xfruta;
    }

    public void setXfruta(int xfruta){
        this.xfruta = xfruta;
    }

    int getYfruta() {
        return yfruta;
    }

    public void setYfruta(int yfruta){
        this.yfruta = yfruta;
    }

}
