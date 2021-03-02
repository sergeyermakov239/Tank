import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemyball {
    public double v=250;
    public double angle=Math.atan(200/150.0);
    public double vx=150;  //можно увеличивать массу, уменьшать скорости , увеличивать dt , тогда
    public double vy=200;  // полёт будет более плавным, траектория будет приближаться  параболе - теперь уже не надо
    public double x=100;
    public double x0=x;
    public double xstroke=-1;
    public double y=100;
    public double y0=y;
    public double vx0=vx;
    public double vy0=vy;
    public double dt = 0.02;
    public double G =10;
    public double m = 1;
    public double k =0.2;
    public int f=0;
    BufferedImage bulletImage;

    public Enemyball(){};
    public void initialize(double a, double b,double c, double d) throws IOException {
        v=a;
        angle=b;
        x=c;
        y=d;
        vx=v*Math.cos(angle);
        vy=v*Math.sin(angle);
        x0=x;
        y0=y;
        vx0=vx;
        vy0=vy;
        bulletImage=ImageIO.read(new File("src/bullets_png35596 (1).png"));
    };
    public void update(double a,double ymin,int direction){   //direction: 1//-1
        if (x==x0){
        dt=a;  };
        x=x0+direction*vx0*m/k*(1-Math.exp(-k*dt/m));
        y=y0+m/k*((vy0+m*G/k)*(1-Math.exp(-k*dt/m))-G*dt );
        vx=vx0*Math.exp(-k*dt/m);
        vy=vy0*Math.exp(-k*dt/m)-G*m/k*(1-Math.exp(-k*dt/m));
        dt=dt+a;
        if (Math.abs(y-ymin)<2.5&&dt!=a&&f==0){
            xstroke=x;
            f=1;
        } else {xstroke=-100000; if (Math.abs(y-ymin)>2.5) {f=0;}};
        //System.out.println(y+" "+y0);
        //System.out.println(y+" "+x+" "+dt);
        /*if (x>1500||y<0){
            x=100;
            y=100;
            dt=a;
        };*/
    };
    public void draw(Graphics g,int direction){
        g.setColor(Color.RED);
        double locationX = bulletImage.getWidth() / 2;
        double locationY = bulletImage.getHeight() / 2;
        double angleInRadians=0;
        if (direction==-1){
            angleInRadians=Math.atan(vy/vx)+Math.PI;}
        else if (direction==1){
            angleInRadians=Math.atan(-vy/vx);
        };
        AffineTransform tx = AffineTransform.getRotateInstance(angleInRadians, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(bulletImage, null), (int)(x), (int)(800-y),50,37, null);
        //g.fillOval((int)(x),(int)(800-y),20,20);
    }
}

