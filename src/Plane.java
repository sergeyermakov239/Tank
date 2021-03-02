import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Plane {
    public double x;
    public double y;
    public double y0;
    public double v=7;
    public double r=40;
    public double t;
    public int f=1;
    public int ff=1;
    public double xstroke=-1;
    public int health=15;
    public Bomb bomb=new Bomb();
    BufferedImage planeImage;
    public Plane() throws IOException {
        this.planeImage= ImageIO.read(new File("src/trying-to-find-a-luftwaffe-pilot-axis-history-forum-luftwaffe-png-1114_450-min.png"));
        bomb.initialize(1,720,v);
    };
    public void initialize(double xx,double yy,double tt){
        x=xx;
        y=yy;
        t=tt;
        y0=y;
        f=1;
    };
    public void update(double dt,int direction){
        if (Math.abs((int)(x)-100)<4.0&&f==-1){ f=1;};
        x=x+f*v*dt;
        y=y0+r*Math.sin(t/8.0);
        t=t+dt;
        if (Math.abs((int)(x)-1300)<4.0&&f==1){ f=-1;};
    };
    public void draw(Graphics g){
        if (f==1){
            g.drawImage(planeImage,(int)(x),(int)(y),120,48,null);
        } else {
            g.drawImage(planeImage,(int)(x+120),(int)(y),-120,48,null);
        };
    };
    public  void shoot(double dt,Graphics g,double ytank) throws IOException {
        bomb.draw(g);
        bomb.update(dt,ff);
        if (Math.abs(y-bomb.y)<5.0){
            if (f!=ff){
                ff=f;
            }
        }
        if (bomb.y>=ytank){
                xstroke =bomb.x;
            bomb.initialize((x+(f+1)*60),y,30);
            ff=f;
        } else {xstroke=-100000;};


    };
    public void drawhealth (Graphics g ){
        g.setColor(Color.GRAY);
        g.fillRect((int)(x-30),((int)(y)-50),180,20);
        g.setColor(Color.RED);
        g.fillRect((int)(x-25),((int)(y)-45),(170/15*health),10);
    };
}
