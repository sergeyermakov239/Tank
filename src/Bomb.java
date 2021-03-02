import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bomb {
    public double x;
    public double y;
    public double x0;
    public double y0;
    public double vx;
    public double vy;
    public double vx0;
    public double G =10;
    public double m = 1;
    public double k =0.1;
    public double dt;
    BufferedImage bombImage;
    public Bomb(){};
    public void initialize(double xx, double yy, double vv) throws IOException {
        x=xx;
        y=yy;
        vx=vv;
        x0=x;
        y0=y;
        vx0=vx;
        vy=0;
        bombImage= ImageIO.read(new File("src/ищьи-png.png"));
    };
    public void draw(Graphics g){
        //g.setColor(Color.RED);
        //g.fillOval((int)(x),(int)(y),20,20);
        g.drawImage(bombImage,(int)(x),(int)(y),20,50,null);
    };
    public void update(double a, int direction){
        if (x==x0){
            dt=a;  };
        x=x0+direction*vx0*m/k*(1-Math.exp(-k*dt/m));
        y=y0-m/k*((m*G/k)*(1-Math.exp(-k*dt/m))-G*dt );
        vx=vx0*Math.exp(-k*dt/m);
        vy=-G*m/k*(1-Math.exp(-k*dt/m));
        dt=dt+a;
    };
}
