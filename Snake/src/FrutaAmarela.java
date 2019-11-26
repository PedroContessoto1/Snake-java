import java.awt.*;

public class FrutaAmarela extends Panel_do_jogo {
    private int xfruta, yfruta, horizontal, vertical;

    FrutaAmarela(int xfruta, int yfruta, int size_fruta){
        this.xfruta = xfruta;
        this.yfruta = yfruta;
        horizontal = size_fruta;
        vertical = size_fruta;
    }

   public void corpo(){

   }

    void desenhar(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(xfruta * horizontal ,yfruta * vertical,horizontal,vertical);
        movimento();

    }

    int getXfruta() {
        return xfruta;
    }

    public void setXfruta(int xfruta){

        this.xfruta = xfruta;
    }

    private void movimento() {
        boolean inverte = true;
        if(inverte){
            for (int x = 0; x < 1; x++){
                this.xfruta += x;
            }
            inverte = false;
        }else{
            for (int x = 0; x < 1; x++){
                this.xfruta += (x * -1);
            }
            inverte = true;
        }
    }

    int getYfruta() {
        return yfruta;
    }

    public void setYfruta(int yfruta){
        this.yfruta = yfruta;
    }

}
