import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    public double x;
    public double y;
    public int health=10;
    BufferedImage tankimage;
    BufferedImage tankwithoutgun;
    BufferedImage gun;
    public Tank() throws IOException {
        tankimage= ImageIO.read(new File("src/tank_PNG1295.png"));
        tankwithoutgun=ImageIO.read(new File("src/soviet tank without gun 1.png"));
        gun=ImageIO.read(new File("src/дуло1.png"));
    };
    public void initialize(int a,int b){
        x=a;
        y=b;
    };
    public void draw (Graphics g,int direction,double angleInRadians){
        if (direction==-1){
        g.drawImage(tankwithoutgun,(int)(x),(int)(y),120,60,null);
            AffineTransform tx = AffineTransform.getRotateInstance(-angleInRadians/*+0.2*/, 63, 63);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(gun, null), (int)(x+74-28*(1-Math.cos(angleInRadians))), (int)(y-20-20*Math.sin(angleInRadians)-10*Math.sin(angleInRadians)*Math.sin(angleInRadians)),80,80, null);
        }
        else if (direction==1){
            g.drawImage(tankwithoutgun,(int)(x+120),(int)(y),-120,60,null);
            AffineTransform tx = AffineTransform.getRotateInstance(-angleInRadians, 63, 63);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(gun, null), (int)(x+25+(74-28*(1-Math.cos(3.1416-angleInRadians)))), (int)(y-20-20*Math.sin(3.1416-angleInRadians)-10*Math.sin(3.1416-angleInRadians)*Math.sin(3.1416-angleInRadians)),-80,80, null);
        }
    };
    public void drawhealth (Graphics g ){
        g.setColor(Color.GRAY);
        g.fillRect((int)(x-30),(int)(y-50),180,20);
        g.setColor(Color.GREEN);
        g.fillRect((int)(x-25),(int)(y-45),(170/10*health),10);
    };
    public void moveright( double dt){
        double v =2.8;
        x+=v*dt;
    };
    public void moveleft(double dt){
        double v=2.8;
        x-=v*dt;
    };
}
