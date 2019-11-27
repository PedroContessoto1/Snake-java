import java.awt.*;

public class Corpo_cobra extends ItemJogo {

    private int xcorpo, ycorpo, horizontal, vertical;

    Corpo_cobra(int xcorpo, int ycorpo, int size_cobra){
        this.xcorpo = xcorpo;
        this.ycorpo = ycorpo;
        horizontal = size_cobra;
        vertical = size_cobra;
    }

    public void desenhar(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(xcorpo * horizontal,ycorpo * vertical,horizontal,vertical);
                                                                                                                                                                                                                    }

    int getXcorpo() {
        return xcorpo;
    }

    public void setXcorpo(int xcorpo){
        this.xcorpo = xcorpo;
    }

    int getYcorpo() {
        return ycorpo;
    }

    public void setYcorpo(int ycorpo){
        this.ycorpo = ycorpo;
    }
}
