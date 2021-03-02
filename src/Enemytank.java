import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemytank {
    public double x=800;
    public double y=140;
    public double x1=-1;
    public int health=2;
    public Enemyball enemyball = new Enemyball();
    BufferedImage tankimage;
    BufferedImage tankwithoutgun;
    BufferedImage gun;
    public Enemytank() throws IOException {
        tankimage= ImageIO.read(new File("src/tank_PNG1320.png"));
        tankwithoutgun=ImageIO.read(new File("src/вражеский танк без дула.png"));
        gun=ImageIO.read(new File("src/m_дуло врага111.png"));
        enemyball.initialize(1,1,x,y);
    };
    public void initialize(int a,int b){
        x=a;
        y=b;
    };
    public void draw (Graphics g,int direction){
        if (direction==1){
        g.drawImage(tankwithoutgun,(int)x,(int)(800-y),120,60,null);
            AffineTransform tx = AffineTransform.getRotateInstance(Math.atan(enemyball.vy0/enemyball.vx0), 50, 50);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(gun, null), (int)(x-5+30*(1-Math.cos(Math.atan(enemyball.vy0/enemyball.vx0)))), (int)(800-(y-10+8+30*Math.sin(Math.atan(enemyball.vy0/enemyball.vx0)))),60,60, null);}
        else if (direction==-1){
            g.drawImage(tankwithoutgun,((int)x+120),(int)(800-y),-120,60,null);
            AffineTransform tx = AffineTransform.getRotateInstance(Math.atan(enemyball.vy0/enemyball.vx0), 50, 50);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(gun, null), (int)(x+120+5-30*(1-Math.cos(Math.atan(enemyball.vy0/enemyball.vx0)))), (int)(800-(y-10+8+30*Math.sin(Math.atan(enemyball.vy0/enemyball.vx0)))),-60,60, null);
        }
    };
    public void move(double dt,double a,int direction){
        if (direction==-1){
        while (x1<a||x1>1500) {
            x1 = (int) (x - 300 + Math.random() * 600);
        };}
        else if (direction==1){
            while (x1>a||x1<0) {
                x1 = (int) (x - 300 + Math.random() * 600);
            };
        };
        if (Math.abs(x1-x)>1){
        if (x1<x){
            x=x-1.5*dt;
        } else {x=x+1.5*dt; };
        } else{x1=-1;};
    };
    public void shoot(double dt,double a,double ymin,Graphics g,int direction) throws IOException {   // a=tank.x
        if (enemyball.vy<0&&enemyball.y<=ymin) {
            double angle = Math.PI/7+Math.random()*Math.PI/6;
            double v=0;
            if (direction==-1) {
                v = Math.abs(x - a) * enemyball.k / enemyball.m / Math.cos(angle);
            } else if (direction==1){
                v = Math.abs(a-x-5/*+90*/) * enemyball.k / enemyball.m / Math.cos(angle);
            }
            enemyball.initialize(v,angle,(x+(direction+1)*60),y);
        };

        enemyball.update(dt,ymin, direction);
        enemyball.draw(g, direction);
    };
    public void drawhealth (Graphics g ){
        g.setColor(Color.GRAY);
        g.fillRect((int)(x-30),((int)(800-y)-50),180,20);
        g.setColor(Color.RED);
        g.fillRect((int)(x-25),((int)(800-y)-45),(170/2*health),10);
    };
}
