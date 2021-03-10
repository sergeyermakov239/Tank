import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ball {
    public double v=250;
    public double angle=Math.atan(200/150.0);
    public double vx=150;  //можно увеличивать массу, уменьшать скорости , увеличивать dt , тогда
    public double vy=200;  // полёт будет более плавным, траектория будет приближаться  параболе - теперь уже не надо
    public double x=100;
    public double x0;
    public  double y0;
    public double xstroke=-1;
    public int f=0;
    public double y=100;
    public double dt = 0.02;
    public double G =10;
    public double dvx;
    public double dvy;
    public double const1 = 0.00013; //плотность
    public double m = 0.9;
    public double k =0.04;  // k0 k=0.01
    BufferedImage bulletimage;

    public Ball(){ };
    public void initialize(double a, double b,double c, double d) throws IOException {
        v=a;
        angle=b;
        x=c+100;
        x0=x;
        y0=y;
        y=d;
        vx=v*Math.cos(angle);
        vy=v*Math.sin(angle);
        this.bulletimage= ImageIO.read(new File("src/bullets_png35596 (1).png"));
    };
    public void update (double a,double ymin,int direction){
        for (int i=0;i<3;i++) {
            dt = a;
        /*dvx=-1*dt/m*k*Math.exp(-1*const1*y)*Math.sqrt(vx*vx+vy*vy)*vx;
        dvy=-1*dt/m*(m*G+k*Math.exp(-1*const1*y)*Math.sqrt(vx*vx+vy*vy)*vy);*/
            if (y<860){
            dvx = -1 * dt / (m * 5.0) * k * Math.pow((1 - 3.65 * Math.pow(10, -3.5) * y), 2.5) * Math.sqrt(vx * vx + vy * vy) * vx;
            dvy = -1 * dt / (m * 5.0) * (m * G + k * Math.pow((1 - 3.65 * Math.pow(10, -3.5) * y), 2.5) * Math.sqrt(vx * vx + vy * vy) * vy);}
            else {dvx=0;
            dvy=-G*dt/5.0;}
            vx = vx + dvx;
            vy = vy + dvy;
            x = x - direction * (vx * dt);
            y = y + (vy * dt);
        }
            //System.out.println(y+" "+y0);
            if (vy < 0 && Math.abs(y - ymin) < 2.5 && x != x0 && f == 0) {
                xstroke = x;
                f = 1;
                //System.out.println("kkk");

            } else {
                xstroke = -1;
                if (Math.abs(y - ymin) > 2.5) {
                    f = 0;
                }
            }
            ;

        /*if (x>1500||y<0){
            x=100;
            y=100;
            vx=150;
            vy=200;
            dvx=0;
            dvy=0;
        };*/

    };
    public void draw(Graphics  g,int direction){
        g.setColor(Color.RED);
        double locationX = bulletimage.getWidth() / 2;
        double locationY = bulletimage.getHeight() / 2;
        double angleInRadians=0;
        if (direction==-1){
         angleInRadians=Math.atan(-vy/vx);}
        else if (direction==1){
            angleInRadians=Math.atan(vy/vx)+Math.PI;
        };
        AffineTransform tx = AffineTransform.getRotateInstance(angleInRadians, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(bulletimage, null), (int)(x-(direction+1)*50), (int)(800-y),50,37, null);
        //g.fillOval((int)(x),(int)(800-y),20,20);
    };
    public void drawline (double a,double ymin,double b,double c,double d,double e, Graphics g,int direction){
         double x1=b;
        double y1=c;
        double vx1=d;
        double vy1=e;
        double dvx1;
        double dvy1;
        dt=0.08;
        g.setColor(Color.BLUE);
        int n=0;
        while (vy1>=0||y1>=ymin) {
            if (y1<860){
            dvx1=-1*dt/(m*5.0)*k*Math.pow((1-3.65*Math.pow(10,-3.5)*y1),2.5)*Math.sqrt(vx1*vx1+vy1*vy1)*vx1;
            dvy1=-1*dt/(m*5.0)*(m*G+k*Math.pow((1-3.65*Math.pow(10,-3.5)*y1),2.5)*Math.sqrt(vx1*vx1+vy1*vy1)*vy1);}
            else {dvx1=0;
            dvy1=-G*dt/5.0;}
            vx1 = vx1 + dvx1;
            vy1 = vy1 + dvy1;
            if (direction==-1){
            x1 = x1 + (vx1 * dt);}
            else {x1 = x1 - (vx1 * dt);};
            y1 = y1 + (vy1 * dt);
            g.setColor(Color.BLUE);
            if (n%30==0) {
            g.fillOval((int)(x1-(direction+1)*50),(int)(800-y1),5,5);}
            n+=1;
        };
    };
    public void boom(Graphics g){
        for (int i=1;i<20;i++){
            g.setColor(Color.YELLOW);
            g.fillOval((int) (x + 10-i), (int) (800 - y + 10-i),2*i,2*i);

        }
    };
}
