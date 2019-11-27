import java.awt.*;

public class FrutaAmarela extends ItemJogo {
    private int xfruta, yfruta, horizontal, vertical;

    FrutaAmarela(int xfruta, int yfruta, int size_fruta){
        this.xfruta = xfruta;
        this.yfruta = yfruta;
        horizontal = size_fruta;
        vertical = size_fruta;
    }

    public void desenhar(Graphics g){

        g.setColor(Color.YELLOW);
        g.fillRect(xfruta * horizontal ,yfruta * vertical,horizontal,vertical);

    }

    public void mover() {
        xfruta+=1;
        if (xfruta == 50){
            xfruta =0;
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
