import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel implements MouseListener, MouseMotionListener {
    public Ball ball = new Ball();
    public Tank tank = new Tank();
    public Enemytank enemytank = new Enemytank();
    public Plane plane = new Plane();
    //public Enemyball enemyball=new Enemyball();
    public int f = 5;
    public int f1 = 3;
    public int enemyboom = 0;
    public int enemyboomdirection = 0;
    public int planeboomdirection = 0;
    public double enemyboomr3 = 0;
    public int time = 0;
    public int timewin = 0;
    public int n = 0;
    int planestroke = 0;
    public int moveright = 0;
    public int tankgo=0;
    public int tankgof=0;
    public int moveleft = 0;
    public int vplus = 0;
    public int vminus = 0;
    public int angleplus = 0;
    public int angleminus = 0;
    public int enemyballdirection = -1;
    public int balldirection = -1;
    public int numberOfTheLevel = -1;
    public int timestudy = -1;
    public int timelevel1 = -1;
    public int timelevel2 = -1;
    public int timelevel3=-1;
    public int timetraektory = -1;
    public int threebegin = 0;
    public int shootbuttonmoved=0;
    public int f3=1;
    public int studyf=0;
    public int finishstudyf=0;
    public int goto2level=0;
    public  int goto3level=0;
    public int showtraektorybuttonmoved=0;
    public long previoustime;
    public long currenttime;
    public long deltatime;
    public double boomr = 0;
    public double enemyboomr = 0;
    public double planeboom = 0;
    public TankGoes sound1=new TankGoes();
    BufferedImage dieImage;
    BufferedImage winImage;
    BufferedImage studyImage;
    BufferedImage study1Image;
    BufferedImage level1Image;
    BufferedImage level2Image;
    BufferedImage level3Image;
    BufferedImage level1backgroundImage;
    BufferedImage level2backgroundImage;
    BufferedImage level0background;
    BufferedImage level3backgroundImage;
    BufferedImage beginStudy;
    BufferedImage beginStudyMouseMoved;
    BufferedImage shootImage;
    BufferedImage showTrektoryImage;
    BufferedImage shootmousemovedImage;
    BufferedImage shootMouseClickedImage;
    BufferedImage showTraektoryMouseMovedImage;
    BufferedImage showTraektoryMouseClickedImage;
    BufferedImage finishstudy;
    BufferedImage finishStudyMouseMoved;
    BufferedImage GoTo2Level;
    BufferedImage GoTo2LevelMouseMoved;
    BufferedImage GoTo3Level;
    BufferedImage GoTo3LevelMouseMoved;
    BufferedImage Kursk;
    BufferedImage Leningrad;
    BufferedImage Stalingrad;
    BufferedImage MapKursk;
    BufferedImage MapLeningrad;
    BufferedImage MapStalingrad;
    BufferedImage floor;
    BufferedImage floor1;

    public Panel() throws IOException {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        tank.initialize(300, 660);
        enemytank.initialize(800, 140);
        plane.initialize(120, 100, 0);

        enemytank.x1 = -1;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        dieImage = ImageIO.read(new File("src/image-created.png"));
        winImage = ImageIO.read((new File("src/477-4772900_icon-you-win-graphic-design-hd-png-download.png")));
        studyImage = ImageIO.read(new File("src/ы1.png"));
        study1Image = ImageIO.read(new File("src/инфа1.png"));
        level1Image = ImageIO.read(new File("src/уровень1.png"));
        level2Image = ImageIO.read(new File("src/ы (1).png"));
        level1backgroundImage = ImageIO.read(new File("src/imgonline-com-ua-Resize-LdS3Mu9x4idZoD9.png"));
        level2backgroundImage = ImageIO.read(new File(/*"src/scale_1200.png"*/"src/немецкий бомбардировщик.jpg"));
        level3backgroundImage = ImageIO.read(new File("src/3background.png"));
        level0background = ImageIO.read(new File("src/level0background.png"));
        shootImage=ImageIO.read(new File("src/shootpng1.png"));
        shootmousemovedImage=ImageIO.read(new File("src/shootmousemoved2.png"));
        shootMouseClickedImage=ImageIO.read(new File("src/shootmouseclicked2.png"));
        showTrektoryImage=ImageIO.read(new File("src/showtraektorypng1.png"));
        showTraektoryMouseMovedImage=ImageIO.read(new File("src/showtraektorymousemoved2.png"));
        showTraektoryMouseClickedImage=ImageIO.read(new File("src/showtraektorymouseclicked2.png"));
        beginStudy=ImageIO.read(new File("src/beginstudy.png"));
        beginStudyMouseMoved=ImageIO.read(new File("src/beginstudymousemoved.png"));
        floor =ImageIO.read(new File("src/level0background0000.png"));
        floor1=ImageIO.read(new File("src/level0background11111.png"));
        finishstudy=ImageIO.read(new File("src/finishstudy.png"));
        finishStudyMouseMoved=ImageIO.read(new File("src/finishstudymousemoved.png"));
        GoTo2Level=ImageIO.read(new File("src/goto2level.png"));
        GoTo2LevelMouseMoved=ImageIO.read(new File("src/goto2levelmousemoved.png"));
        GoTo3Level=ImageIO.read(new File("src/goto3level.png"));
        GoTo3LevelMouseMoved=ImageIO.read(new File("src/goto3levelmousemoved.png"));
        Kursk=ImageIO.read(new File("src/Курская битва.jpg"));
        Leningrad=ImageIO.read(new File("src/Битва за Ленинград.jpg"));
        Stalingrad=ImageIO.read(new File("src/Сталинградская битва 2.jpg"));
        level3Image=ImageIO.read(new File("src/уровень 3 1.png"));
        MapKursk=ImageIO.read(new File("src/карта ссср курск.jpg"));
        MapLeningrad=ImageIO.read(new File("src/карта ссср ленинград.jpg"));
        MapStalingrad=ImageIO.read(new File("src/карта ссср сталинград.jpg"));
    }

    ;

    @Override
    public void paintComponent(Graphics g) {
         //numberOfTheLevel=3;                                      //надо будет потом убрать!!!!
        currenttime = System.currentTimeMillis();
        deltatime = currenttime - previoustime;
        previoustime = currenttime;
        if (numberOfTheLevel == -1 || timestudy >= 0) {
            if (timestudy >= 0 && timestudy <= 200) {
                g.drawImage(study1Image, 100, 0, 1131, 1600, null);
                timestudy -= deltatime/5.0;
            } else {
                g.drawImage(studyImage, 0, 0, 1425, 600, null);
                g.setColor(Color.BLACK);
                g.drawImage(beginStudy,50,500,272,60,null);
                if (studyf==1){
                    g.drawImage(beginStudyMouseMoved,50,500,272,60,null);
                }
                //g.drawRect(50, 500, 200, 20);
               // g.drawString("Начать обучение", 55, 513);
            }
        } else if (numberOfTheLevel == 0 || timelevel1 >= 0) {                                                              //0 level started
            if (timelevel1 >= 0 && timelevel1 <= 800) {
                if (timelevel1>=680) {
                g.drawImage(level1Image, 100, 0, 1131, 1600, null);}
                else {g.drawImage(Kursk,600,0,882,740,null);
                g.drawImage(MapKursk,0,0,592,740,null);}
                timelevel1 -= deltatime / 6.5;
                enemytank.enemyball.vy = -1;
                enemytank.enemyball.y = -1;
            } else {
                g.drawImage(level0background, 0, 0, 1500, 800, null);
                g.setColor(Color.BLACK);
                g.drawImage(shootImage,50,15,110,40,null);
                g.drawImage(showTrektoryImage,50,58,180,40,null);
                g.drawImage(finishstudy,50,150,187,40,null);
                if (shootbuttonmoved==1){
                   /* Color color = new Color(0, 152, 253, 42);
                    g.setColor(color);
                    g.fillRect(50, 20, 100, 20);
                    g.setColor(Color.BLACK);*/
                    g.drawImage(shootmousemovedImage,50,15,110,40,null);
                }
                if (shootbuttonmoved==2){
                    /*Color color = new Color(0, 122, 253, 232);
                    g.setColor(color);
                    g.fillRect(50, 20, 100, 20);
                    g.setColor(Color.BLACK);*/
                    g.drawImage(shootMouseClickedImage,50,15,110,40,null);
                    f3=1;
                }
                //g.drawRect(50, 20, 100, 20);
               // g.drawString("Стрелять", 55, 33);

                if (showtraektorybuttonmoved==1&&f3==1){
                    /*Color color = new Color(0, 152, 253, 42);
                    g.setColor(color);
                    g.fillRect(50, 50, 130, 20);
                    g.setColor(Color.BLACK);*/
                    g.drawImage(showTraektoryMouseMovedImage,50,58,180,40,null);
                } else
                if (f3==0){
                    /*Color color = new Color(0, 122, 253, 232);
                    g.setColor(color);
                    g.fillRect(50, 50, 130, 20);
                    g.setColor(Color.BLACK);*/
                    g.drawImage(showTraektoryMouseClickedImage,50,58,180,40,null);
                } else if (timetraektory==0){f3=0;}
                if (timetraektory==400){f3=0;}
                if (timetraektory==0){f3=1;}
                if (finishstudyf==1){
                    g.drawImage(finishStudyMouseMoved,50,150,187,40,null);
                }



                //g.drawRect(50, 50, 130, 20);
                //g.drawString("Показать траекторию", 55, 63);
                g.setFont(new Font("Impact",Font.PLAIN, 14));
                g.drawString("Скорость равна=" + (int) (ball.v), 50, 115);
                g.drawString("Угол равен=" + (int) (ball.angle * 180 / Math.PI), 50, 133);
                //g.drawString("Закончить обучение", 55, 163);
                //g.drawRect(50, 150, 140, 20);
                g.drawString("Обучение", 700, 50);
                g.setColor(Color.GRAY);
                //g.fillRect(0, 720, 1500, 80);
                g.drawImage(floor,0, 720, 1500, 80,null);

                if (f == 1) {
                    if (timetraektory >= 0) {
                        ball.drawline(0.001, ball.y, ball.x, ball.y, ball.vx, ball.vy, g, -1);
                        //System.out.println("jjj"+timetraektory);
                        timetraektory -= deltatime / 10.0;
                        try {
                            ball.initialize(ball.v, ball.angle, tank.x, (800 - tank.y));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    ;
                    if (timetraektory == -1) {
                        f = -1;
                    }
                    ;
                }
                ;
                if (f1 == 1) {
                    if (ball.y >= (750 - tank.y)) {
                        ball.draw(g, -1);
                        //System.out.println("jjj");
                        ball.update((deltatime / 200.0), ball.y0, -1);
                    } else {
                        if (boomr < 10) {
                            g.setColor(Color.RED);
                        } else if (boomr < 20) {
                            Color color = new Color(250, 102, 4);
                            g.setColor(color);
                        } else if (boomr < 30) {
                            g.setColor(Color.ORANGE);
                        } else if (boomr < 40) {
                            g.setColor(Color.YELLOW);
                        }
                        ;
                        g.fillOval((int) (ball.x + 30 - boomr), (int) (800 - ball.y + 10 - boomr), 2 * (int) (boomr), 2 * (int) (boomr));
                        if (boomr < 39) {
                            boomr = boomr + deltatime / 30.0;
                        } else {
                            boomr = 0;
                            f1 = 0;
                        }
                        ;
                    }
                }
                ;
                if (moveright == 1 && tank.x < 1380) {
                    tank.moveright(deltatime / 10.0);
                } else if (moveleft == 1 && tank.x > 0) {
                    tank.moveleft(deltatime / 10.0);
                }
                ;
                tank.draw(g, -1,ball.angle);
                tank.drawhealth(g);
                sound1.bool=tankgo;
                if (vplus == 1) {
                    ball.v += 0.2;
                } else if (vminus == 1) {
                    ball.v -= 0.2;
                }
                ;
                if (angleplus == 1&&ball.angle<=80/180.0*3.1416) {
                    ball.angle = ball.angle + 1 / 1800.0 * Math.PI;
                } else if (angleminus == 1&&ball.angle>=0/180.0*3.1416) {
                    ball.angle = ball.angle - 1 / 1800.0 * Math.PI;
                }
                if (f == 1) {
                    f1 = 0;
                }
                ;
                //f = 0;
            }
        } else if (numberOfTheLevel == 1) {
            if (timelevel2 >= 0) {
                if (timelevel2>= 700  ){
                g.drawImage(level2Image, 0, 0, 1500, 2121, null);}
                else {g.drawImage(Leningrad,586,0,876,730,null);
                g.drawImage(MapLeningrad,0,0,584,730,null);}
                if (timelevel2 == 0) {
                    numberOfTheLevel = 2;
                    try {
                        plane.bomb.initialize(120, 100, 30);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ;
                timelevel2 -= deltatime / 10.0;
            } else {
                g.drawImage(level1backgroundImage, 0, 0, 1500, 800, null);
                g.setColor(Color.BLACK);                                                                           //1 level started
                g.drawImage(shootImage,50,15,110,40,null);
                sound1.bool=tankgo;
                g.drawImage(showTrektoryImage,50,58,180,40,null);
                g.drawImage(GoTo2Level,50,140,178,40,null);
                if (goto2level==1){
                    g.drawImage(GoTo2LevelMouseMoved,50,140,178,40,null);
                }
                if (shootbuttonmoved==1){
                   /* Color color = new Color(0, 152, 253, 42);
                    g.setColor(color);
                    g.fillRect(50, 20, 100, 20);
                    g.setColor(Color.BLACK);*/
                    g.drawImage(shootmousemovedImage,50,15,110,40,null);
                }
                if (shootbuttonmoved==2){
                    /*Color color = new Color(0, 122, 253, 232);
                    g.setColor(color);
                    g.fillRect(50, 20, 100, 20);
                    g.setColor(Color.BLACK);*/
                    g.drawImage(shootMouseClickedImage,50,15,110,40,null);
                    f3=1;
                }
                //g.drawRect(50, 20, 100, 20);
                // g.drawString("Стрелять", 55, 33);

                if (showtraektorybuttonmoved==1&&f3==1){
                    /*Color color = new Color(0, 152, 253, 42);
                    g.setColor(color);
                    g.fillRect(50, 50, 130, 20);
                    g.setColor(Color.BLACK);*/
                    g.drawImage(showTraektoryMouseMovedImage,50,58,180,40,null);
                } else
                if (f3==0){
                    /*Color color = new Color(0, 122, 253, 232);
                    g.setColor(color);
                    g.fillRect(50, 50, 130, 20);
                    g.setColor(Color.BLACK);*/
                    g.drawImage(showTraektoryMouseClickedImage,50,58,180,40,null);
                } else if (timetraektory==0){f3=0;}
                if (timetraektory==400){f3=0;}
                if (timetraektory==0){f3=1;}


                //g.drawRect(50, 50, 130, 20);
                //g.drawString("Показать траекторию", 55, 63);
                g.setFont(new Font("Impact",Font.PLAIN, 14));
                g.drawString("Скорость равна=" + (int) (ball.v), 50, 115);
                g.drawString("Угол равен=" + (int) (ball.angle * 180 / Math.PI), 50, 133);
                //g.drawRect(50, 140, 140, 20);
                //g.drawString("Перейти на 2 уровень", 55, 153);
                g.setColor(Color.GRAY);
                //g.fillRect(0, 720, 1500, 80);
                g.drawImage(floor,0,720,1500,80,null);
                //enemyball.draw(g);
                //enemyball.update(0.03);
                if (tank.x < enemytank.x) {
                    enemytank.draw(g, 1);
                } else {
                    enemytank.draw(g, -1);
                }
                ;
                enemytank.drawhealth(g);
                if ((int) (ball.xstroke) >= (enemytank.x/*-10*/) && (int) (ball.xstroke) <= (enemytank.x + 120/*+130*/)) {
                    enemytank.health -= 1;
                    //System.out.println(tank.health);
                }
                ;
                if (Math.abs(enemytank.enemyball.y - enemytank.enemyball.y0 + 60) < 3.5 && enemytank.enemyball.y != enemytank.enemyball.y0/*&&Math.abs(enemytank.enemyball.x - enemytank.enemyball.x0) > 5.0*/) {
                    if (tank.x < enemytank.x) {
                        enemyballdirection = -1;
                    } else {
                        enemyballdirection = 1;
                    }
                    ;
                }
                ;
            /*if (Math.abs(ball.y-ball.y0)<2.5&&ball.y!=ball.y0){
                if (tank.x<enemytank.x){
                    balldirection=-1;
                }
                else {
                    balldirection=1;
                };
            };*/
                if (enemytank.enemyball.xstroke > 0) {
                    enemyboom = 1;
                    enemytank.enemyball.xstroke = -10000;
                    enemyboomdirection = enemyballdirection;
                }
                ;
                if (enemyboom == 1) {
                    if (enemyboomr < 10) {
                        g.setColor(Color.RED);
                    } else if (enemyboomr < 20) {
                        Color color = new Color(250, 102, 4);
                        g.setColor(color);
                    } else if (enemyboomr < 30) {
                        g.setColor(Color.ORANGE);
                    } else if (enemyboomr < 40) {
                        g.setColor(Color.YELLOW);
                    }
                    ;
                    //if (boomr==0&&tank.x<enemytank.x) {boomdirection=1;} else if (boomr==0&&tank.x>enemytank.x) {boomdirection=-1;};
                    if (enemyboomdirection == -1) {
                        g.fillOval((int) (enemytank.enemyball.x + 30 - enemyboomr), (int) (800 - enemytank.enemyball.y + 10 - enemyboomr), 2 * (int) (enemyboomr), 2 * (int) (enemyboomr));
                    } else {
                        g.fillOval((int) (enemytank.enemyball.x + 30 - enemyboomr), (int) (800 - enemytank.enemyball.y + 10 - enemyboomr), 2 * (int) (enemyboomr), 2 * (int) (enemyboomr));
                    }
                    if (enemyboomr < 39) {
                        enemyboomr = enemyboomr + deltatime / 30.0;
                    } else {
                        enemyboomr = 0;
                        enemyboom = 0;
                    }
                    ;
                }
                ;
                try {
                    if (enemyboom == 0) {
                        enemytank.shoot(deltatime / 200.0, tank.x, 82, g, enemyballdirection);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (enemytank.enemyball.xstroke >= tank.x && enemytank.enemyball.xstroke <= (tank.x + 120)) {
                    tank.health -= 1;
                    //System.out.println(tank.health);
                }
                ;
                if (tank.x < enemytank.x) {
                    enemytank.move(deltatime / 20.0, tank.x, -1);
                } else {
                    enemytank.move(deltatime / 20.0, tank.x, 1);
                }
                ;
                if (f == 1) {
                    if (timetraektory >= 0) {
                        ball.drawline(0.05, ball.y, ball.x, ball.y, ball.vx, ball.vy, g, balldirection);
                        //System.out.println("jjj"+timetraektory);
                        timetraektory -= deltatime / 10.0;
                        try {
                            ball.initialize(ball.v, ball.angle, tank.x, (800 - tank.y));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    ;
                    if (timetraektory == -1) {
                        f = -1;
                    }
                    ;
                }
                ;
                if (f1 == 1) {
                    if (ball.y >= (750 - tank.y)) {
                        ball.draw(g, balldirection);
                        ball.update(deltatime / 200.0, ball.y0, balldirection);
                    } else {
                        ball.xstroke = -10000;
                        if (boomr < 10) {
                            g.setColor(Color.RED);
                        } else if (boomr < 20) {
                            Color color = new Color(250, 102, 4);
                            g.setColor(color);
                        } else if (boomr < 30) {
                            g.setColor(Color.ORANGE);
                        } else if (boomr < 40) {
                            g.setColor(Color.YELLOW);
                        }
                        ;
                        //if (boomr==0&&tank.x<enemytank.x) {boomdirection=1;} else if (boomr==0&&tank.x>enemytank.x) {boomdirection=-1;};
                        if (balldirection == -1) {
                            g.fillOval((int) (ball.x + 30 - boomr), (int) (800 - ball.y + 10 - boomr), 2 * (int) (boomr), 2 * (int) (boomr));
                        } else {
                            g.fillOval((int) (ball.x - 80 - boomr), (int) (800 - ball.y + 10 - boomr), 2 * (int) (boomr), 2 * (int) (boomr));
                        }
                        if (boomr < 39) {
                            boomr = boomr + deltatime / 30.0;
                        } else {
                            boomr = 0;
                            f1 = 0;
                        }
                        ;
                    }
                }
                ;
                if (moveright == 1 && tank.x < 1380) {
                    tank.moveright(deltatime / 10.0);
                } else if (moveleft == 1 && tank.x > 0) {
                    tank.moveleft(deltatime / 10.0);
                }
                ;
                if ((tank.x < enemytank.x)) {
                    tank.draw(g, -1,ball.angle);
                } else {
                    tank.draw(g, 1,ball.angle);
                }
                ;
                tank.drawhealth(g);
                if (vplus == 1) {
                    ball.v += 0.2;
                } else if (vminus == 1) {
                    ball.v -= 0.2;
                }
                ;
                if (angleplus == 1&&ball.angle<=80/180.0*3.1416) {
                    ball.angle = ball.angle + 1 / 1800.0 * Math.PI;
                } else if (angleminus == 1&&ball.angle>=0/180.0*3.1416) {
                    ball.angle = ball.angle - 1 / 1800.0 * Math.PI;
                }
                if (f == 1) {
                    f1 = 0;
                }
                ;
                // f = 0;

                if (tank.health <= 0 && time < 100) {
                    g.drawImage(dieImage, 0, 0, 1500, 800, null);
                    time += deltatime / 10.0;
                    if (Math.abs(time - 99)<2.0) {
                        tank.health = 10;
                        enemytank.health = 2;
                        time = 0;
                    }
                }
                ;
                if (enemytank.health <= 0 && timewin < 100) {
                    g.drawImage(winImage, 0, 0, 1500, 800, null);
                    timewin += deltatime / 10.0;
                    if (Math.abs(timewin- 99)<2.0) {
                        tank.health = 10;
                        enemytank.health = 2;
                        timewin = 0;
                        //nuberofthelevel=2;
                        timelevel2 = 800;
                        enemyboomr=0;
                        try {
                            plane.bomb.initialize(120, 100, 30);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ;                                                                                          //1 level ended
            }
            ;

        } else if (numberOfTheLevel == 2) {                                                                    //level 2 started
            if (timelevel3 >= 0) {
                if (timelevel3>= 700  ){
                    g.drawImage(level3Image, 0, 0, 1500, 2121, null);}
                else {g.drawImage(Stalingrad,755,0,563,750,null);
                g.drawImage(MapStalingrad,150,0,600,750,null);}
                if (timelevel3 == 0) {
                    numberOfTheLevel = 3;
                    tank.health = 10;
                    plane.health = 15;
                    timewin = 0;
                    timelevel3=-1;
                }
                ;
                timelevel3 -= deltatime / 10.0;} else{
            g.drawImage(level2backgroundImage, 0, 0, 1500, 800, null);
            g.setColor(Color.BLACK);
                sound1.bool=tankgo;
            g.drawImage(shootImage,50,15,110,40,null);
            g.drawImage(showTrektoryImage,50,58,180,40,null);
            g.drawImage(GoTo3Level,50,140,180,40,null);
            if (goto3level==1){
                g.drawImage(GoTo3LevelMouseMoved,50,140,180,40,null);
            }
            if (shootbuttonmoved==1){
                   /* Color color = new Color(0, 152, 253, 42);
                    g.setColor(color);
                    g.fillRect(50, 20, 100, 20);
                    g.setColor(Color.BLACK);*/
                g.drawImage(shootmousemovedImage,50,15,110,40,null);
            }
            if (shootbuttonmoved==2){
                    /*Color color = new Color(0, 122, 253, 232);
                    g.setColor(color);
                    g.fillRect(50, 20, 100, 20);
                    g.setColor(Color.BLACK);*/
                g.drawImage(shootMouseClickedImage,50,15,110,40,null);
                f3=1;
            }
            //g.drawRect(50, 20, 100, 20);
            // g.drawString("Стрелять", 55, 33);

            if (showtraektorybuttonmoved==1&&f3==1){
                    /*Color color = new Color(0, 152, 253, 42);
                    g.setColor(color);
                    g.fillRect(50, 50, 130, 20);
                    g.setColor(Color.BLACK);*/
                g.drawImage(showTraektoryMouseMovedImage,50,58,180,40,null);
            } else
            if (f3==0){
                    /*Color color = new Color(0, 122, 253, 232);
                    g.setColor(color);
                    g.fillRect(50, 50, 130, 20);
                    g.setColor(Color.BLACK);*/
                g.drawImage(showTraektoryMouseClickedImage,50,58,180,40,null);
            } else if (timetraektory==0){f3=0;}
            if (timetraektory==400){f3=0;}
            if (timetraektory==0){f3=1;}


            //g.drawRect(50, 50, 130, 20);
            //g.drawString("Показать траекторию", 55, 63);
            g.setFont(new Font("Impact",Font.PLAIN, 14));
            g.drawString("Скорость равна=" + (int) (ball.v), 50, 115);
            g.drawString("Угол равен=" + (int) (ball.angle * 180 / Math.PI), 50, 133);
            g.setColor(Color.GRAY);
            //g.fillRect(0, 720, 1500, 80);
            //g.fillRect(900,500,600,300);
            g.drawImage(floor,0,720,1500,80,null);
            plane.update(deltatime / 70.0, 1);

            if (plane.xstroke > 0) {
                planeboom = 1;
                planeboomdirection = (int) (plane.xstroke);
                plane.xstroke = -100000;

            }
            ;
            if (planeboom == 0) {
                try {
                    plane.shoot(deltatime / 150.0, g, 720);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            } else if (planeboom == 1) {
                if (enemyboomr < 10) {
                    g.setColor(Color.RED);
                } else if (enemyboomr < 20) {
                    Color color = new Color(250, 102, 4);
                    g.setColor(color);
                } else if (enemyboomr < 30) {
                    g.setColor(Color.ORANGE);
                } else if (enemyboomr < 40) {
                    g.setColor(Color.YELLOW);
                }
                ;
                //if (boomr==0&&tank.x<enemytank.x) {boomdirection=1;} else if (boomr==0&&tank.x>enemytank.x) {boomdirection=-1;};
                if (plane.f == 1) {
                    g.fillOval((int) (planeboomdirection - enemyboomr), (int) (tank.y + 60 - enemyboomr), 2 * (int) (enemyboomr), 2 * (int) (enemyboomr));
                } else {
                    g.fillOval((int) (planeboomdirection - enemyboomr), (int) (tank.y + 60 - enemyboomr), 2 * (int) (enemyboomr), 2 * (int) (enemyboomr));
                }
                if (enemyboomr < 39) {
                    enemyboomr = enemyboomr + deltatime / 30.0;
                } else {
                    enemyboomr = 0;
                    planeboom = 0;
                    try {
                        plane.bomb.initialize((plane.x + (plane.f + 1) * 60), plane.y, 30);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                    plane.xstroke = -100000;
                    ;
                }
                ;
            }
            plane.draw(g);
            plane.drawhealth(g);
            if (plane.xstroke >= tank.x && plane.xstroke <= (tank.x + 120)) {
                tank.health -= 1;
                //System.out.println(tank.health);
            }
            ;
            if (ball.x >= plane.x && ball.x <= (plane.x + 120) && (800 - ball.y) >= (plane.y - 48) && (800 - ball.y) <= (plane.y)) {
                if (planestroke == 0) {
                    plane.health -= 1;
                    planestroke = 1;
                }
                //System.out.println(planestroke);
            } else {
                planestroke = 0; /*System.out.println(planestroke);*/
            }
            ;
            if (f == 1) {
                if (timetraektory >= 0) {
                    ball.drawline(0.05, ball.y, ball.x, ball.y, ball.vx, ball.vy, g, -1);
                    //System.out.println("jjj"+timetraektory);
                    timetraektory -= deltatime / 10.0;
                    try {
                        ball.initialize(ball.v, ball.angle, tank.x, (800 - tank.y));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                ;
                if (timetraektory == -1) {
                    f = -1;
                }
                ;
            }
            ;
            if (f1 == 1) {
                if (ball.y >= (750 - tank.y)) {
                    ball.draw(g, -1);
                    ball.update(deltatime / 200.0, ball.y0, -1);
                } else {
                    if (boomr < 10) {
                        g.setColor(Color.RED);
                    } else if (boomr < 20) {
                        Color color = new Color(250, 102, 4);
                        g.setColor(color);
                    } else if (boomr < 30) {
                        g.setColor(Color.ORANGE);
                    } else if (boomr < 40) {
                        g.setColor(Color.YELLOW);
                    }
                    ;
                    g.fillOval((int) (ball.x + 30 - boomr), (int) (800 - ball.y + 10 - boomr), 2 * (int) (boomr), 2 * (int) (boomr));
                    if (boomr < 39) {
                        boomr = boomr + deltatime / 30.0;
                    } else {
                        boomr = 0;
                        f1 = 0;
                    }
                    ;
                }
            }
            ;
            if (moveright == 1 && tank.x < 1380) {
                tank.moveright(deltatime / 10.0);
            } else if (moveleft == 1 && tank.x > 0) {
                tank.moveleft(deltatime / 10.0);
            }
            ;
            tank.draw(g, -1,ball.angle);
            tank.drawhealth(g);
            if (vplus == 1) {
                ball.v += 0.2;
            } else if (vminus == 1) {
                ball.v -= 0.2;
            }
            ;
            if (angleplus == 1&&ball.angle<=80/180.0*3.1416) {
                ball.angle = ball.angle + 1 / 1800.0 * Math.PI;
            } else if (angleminus == 1&&ball.angle>=0/180.0*3.1416) {
                ball.angle = ball.angle - 1 / 1800.0 * Math.PI;
            }
            if (f == 1) {
                f1 = 0;
            }
            ;
            //f = 0;

            if (tank.health <= 0 && time < 100) {
                g.drawImage(dieImage, 0, 0, 1500, 800, null);
                time += deltatime / 10;
                if (Math.abs(time- 99)<2.0) {
                    tank.health = 10;
                    plane.health = 15;
                    time = 0;
                }
            }
            ;
            if (plane.health <= 0 && timewin < 100) {
                g.drawImage(winImage, 0, 0, 1500, 800, null);
                timewin += deltatime / 10;
                if (Math.abs(timewin - 99)<2.0) {
                    tank.health = 10;
                    plane.health = 15;
                    timewin = 0;
                    //numberOfTheLevel = 3;
                    timelevel3=800;
                }
            }
            ;
        } }                                                                                                    //level2 ended


        else if (numberOfTheLevel == 3) {                                                                           //level3 started
            g.drawImage(level3backgroundImage, 0, 0, 1500, 800, null);
            g.setColor(Color.BLACK);
            sound1.bool=tankgo;
            g.drawImage(shootImage,50,15,110,40,null);
            g.drawImage(showTrektoryImage,50,58,180,40,null);
            if (shootbuttonmoved==1){
                   /* Color color = new Color(0, 152, 253, 42);
                    g.setColor(color);
                    g.fillRect(50, 20, 100, 20);
                    g.setColor(Color.BLACK);*/
                g.drawImage(shootmousemovedImage,50,15,110,40,null);
            }
            if (shootbuttonmoved==2){
                    /*Color color = new Color(0, 122, 253, 232);
                    g.setColor(color);
                    g.fillRect(50, 20, 100, 20);
                    g.setColor(Color.BLACK);*/
                g.drawImage(shootMouseClickedImage,50,15,110,40,null);
                f3=1;
            }
            //g.drawRect(50, 20, 100, 20);
            // g.drawString("Стрелять", 55, 33);

            if (showtraektorybuttonmoved==1&&f3==1){
                    /*Color color = new Color(0, 152, 253, 42);
                    g.setColor(color);
                    g.fillRect(50, 50, 130, 20);
                    g.setColor(Color.BLACK);*/
                g.drawImage(showTraektoryMouseMovedImage,50,58,180,40,null);
            } else
            if (f3==0){
                    /*Color color = new Color(0, 122, 253, 232);
                    g.setColor(color);
                    g.fillRect(50, 50, 130, 20);
                    g.setColor(Color.BLACK);*/
                g.drawImage(showTraektoryMouseClickedImage,50,58,180,40,null);
            } else if (timetraektory==0){f3=0;}
            if (timetraektory==400){f3=0;}
            if (timetraektory==0){f3=1;}


            //g.drawRect(50, 50, 130, 20);
            //g.drawString("Показать траекторию", 55, 63);
            g.setFont(new Font("Impact",Font.PLAIN, 14));
            g.drawString("Скорость равна=" + (int) (ball.v), 50, 115);
            g.drawString("Угол равен=" + (int) (ball.angle * 180 / Math.PI), 50, 133);
            g.setColor(Color.GRAY);
            g.drawImage(floor,0, 720, 1500, 80,null);
            g.drawImage(floor1,800, 600, 700, 200,null);
            if (threebegin < 2) {
                enemytank.initialize(1000, 260);
                plane.initialize(100, 100, 0);
                try {
                    plane.bomb.initialize(100, 100, 30);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                enemytank.enemyball.vy = -1;
                plane.xstroke = -10000;
                enemytank.enemyball.y = 0;
            }
            ;
            threebegin += 1;
            if (f == 1) {
                if (timetraektory >= 0) {
                    ball.drawline(0.05, (ball.y + 100), ball.x, ball.y, ball.vx, ball.vy, g, -1);
                    //System.out.println("jjj"+timetraektory);
                    timetraektory -= deltatime / 10.0;
                    try {
                        ball.initialize(ball.v, ball.angle, tank.x, (800 - tank.y));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                ;
                if (timetraektory == -1) {
                    f = -1;
                }
                ;
            }
            ;
            if (f1 == 1) {
                if (ball.y >= (750 - tank.y + 120) || ball.vy > 0 || ball.x < 800 && ball.y >= (750 - tank.y)) {
                    ball.draw(g, -1);
                    ball.update(deltatime / 200.0, (750 - tank.y + 120), -1);
                } else {
                    if (boomr < 10) {
                        g.setColor(Color.RED);
                    } else if (boomr < 20) {
                        Color color = new Color(250, 102, 4);
                        g.setColor(color);
                    } else if (boomr < 30) {
                        g.setColor(Color.ORANGE);
                    } else if (boomr < 40) {
                        g.setColor(Color.YELLOW);
                    }
                    ;
                    g.fillOval((int) (ball.x + 30 - boomr), (int) (800 - ball.y + 10 - boomr), 2 * (int) (boomr), 2 * (int) (boomr));
                    if (boomr < 39) {
                        boomr = boomr + deltatime / 30.0;
                    } else {
                        boomr = 0;
                        f1 = 0;
                    }
                    ;
                }
            }
            ;
            if (moveright == 1 && tank.x < 680) {
                tank.moveright(deltatime / 10.0);
            } else if (moveleft == 1 && tank.x > 0) {
                tank.moveleft(deltatime / 10.0);
            }
            ;
            tank.draw(g, -1,ball.angle);
            tank.drawhealth(g);
            if (enemytank.health > 0) {
                enemytank.draw(g, 1);
                enemytank.drawhealth(g);
                if ((int) (ball.xstroke) >= (enemytank.x/*-10*/) && (int) (ball.xstroke) <= (enemytank.x + 120/*+130*/)) {
                    enemytank.health -= 1;
                    //System.out.println(tank.health);
                }
                ;
                if (Math.abs(enemytank.enemyball.y - enemytank.enemyball.y0) < 2.5 && enemytank.enemyball.y != enemytank.enemyball.y0) {
                    if (tank.x < enemytank.x) {
                        enemyballdirection = -1;
                    } else {
                        enemyballdirection = 1;
                    }
                    ;
                }
                ;
                if (enemytank.enemyball.xstroke > 0) {
                    enemyboom = 1;
                    enemytank.enemyball.xstroke = -10000;
                    enemyboomdirection = enemyballdirection;
                }
                ;
                if (enemyboom == 1) {
                    if (enemyboomr < 10) {
                        g.setColor(Color.RED);
                    } else if (enemyboomr < 20) {
                        Color color = new Color(250, 102, 4);
                        g.setColor(color);
                    } else if (enemyboomr < 30) {
                        g.setColor(Color.ORANGE);
                    } else if (enemyboomr < 40) {
                        g.setColor(Color.YELLOW);
                    }
                    ;
                    //if (boomr==0&&tank.x<enemytank.x) {boomdirection=1;} else if (boomr==0&&tank.x>enemytank.x) {boomdirection=-1;};
                    if (enemyboomdirection == -1) {
                        g.fillOval((int) (enemytank.enemyball.x + 30 - enemyboomr), (int) (800 - enemytank.enemyball.y + 10 - enemyboomr), 2 * (int) (enemyboomr), 2 * (int) (enemyboomr));
                    } else {
                        g.fillOval((int) (enemytank.enemyball.x + 30 - enemyboomr), (int) (800 - enemytank.enemyball.y + 10 - enemyboomr), 2 * (int) (enemyboomr), 2 * (int) (enemyboomr));
                    }
                    if (enemyboomr < 39) {
                        enemyboomr = enemyboomr + deltatime / 30.0;
                    } else {
                        enemyboomr = 0;
                        enemyboom = 0;
                    }
                    ;
                }
                ;
                try {
                    if (enemyboom == 0) {
                        enemytank.shoot(deltatime / 200.0, tank.x, 800 - tank.y - 60, g, enemyballdirection);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
                if (enemytank.enemyball.xstroke >= tank.x && enemytank.enemyball.xstroke <= (tank.x + 120)) {
                    tank.health -= 1;
                    //System.out.println(tank.health);
                }
                ;

                enemytank.move(deltatime / 20.0, 800, -1);
            }

            if (vplus == 1) {
                ball.v += 0.2;
            } else if (vminus == 1) {
                ball.v -= 0.2;
            }
            ;
            if (angleplus == 1&&ball.angle<=80/180.0*3.1416) {
                ball.angle = ball.angle + 1 / 1800.0 * Math.PI;
            } else if (angleminus == 1&&ball.angle>=0/180.0*3.1416) {
                ball.angle = ball.angle - 1 / 1800.0 * Math.PI;
            }
            if (f == 1) {
                f1 = 0;
            }
            ;
            if (plane.health > 0) {
                plane.draw(g);
                if (plane.bomb.x < 800) {
                    /*plane.shoot(deltatime/200.0,g,720)*/
                    if (plane.xstroke > 0) {
                        planeboom = 1;
                        planeboomdirection = (int) (plane.xstroke);
                        plane.xstroke = -100000;

                    }
                    ;
                    if (planeboom == 0) {
                        try {
                            plane.shoot(deltatime / 150.0, g, 720);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ;
                    } else if (planeboom == 1) {
                        if (enemyboomr3 < 10) {
                            g.setColor(Color.RED);
                        } else if (enemyboomr3 < 20) {
                            Color color = new Color(250, 102, 4);
                            g.setColor(color);
                        } else if (enemyboomr3 < 30) {
                            g.setColor(Color.ORANGE);
                        } else if (enemyboomr3 < 40) {
                            g.setColor(Color.YELLOW);
                        }
                        ;
                        //if (boomr==0&&tank.x<enemytank.x) {boomdirection=1;} else if (boomr==0&&tank.x>enemytank.x) {boomdirection=-1;};
                        if (plane.f == 1) {
                            g.fillOval((int) (planeboomdirection - enemyboomr3), (int) (720 - enemyboomr3), 2 * (int) (enemyboomr3), 2 * (int) (enemyboomr3));
                        } else {
                            g.fillOval((int) (planeboomdirection - enemyboomr3), (int) (720 - enemyboomr3), 2 * (int) (enemyboomr3), 2 * (int) (enemyboomr3));
                        }
                        if (enemyboomr3 < 39) {
                            enemyboomr3 = (enemyboomr3 + deltatime / 30.0);
                        } else {
                            enemyboomr3 = 0;
                            planeboom = 0;
                            try {
                                plane.bomb.initialize((plane.x + (plane.f + 1) * 60), plane.y, 30);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ;
                            plane.xstroke = -100000;
                            ;
                        }
                        ;
                    }
                    ;
                } else {//plane.shoot(deltatime/200.0,g,600)
                    if (plane.xstroke > 0) {
                        planeboom = 1;
                        planeboomdirection = (int) (plane.xstroke);
                        plane.xstroke = -100000;

                    }
                    ;
                    if (planeboom == 0) {
                        try {
                            plane.shoot(deltatime / 150.0, g, 600);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ;
                    } else if (planeboom == 1) {
                        if (enemyboomr3 < 10) {
                            g.setColor(Color.RED);
                        } else if (enemyboomr3 < 20) {
                            Color color = new Color(250, 102, 4);
                            g.setColor(color);
                        } else if (enemyboomr3 < 30) {
                            g.setColor(Color.ORANGE);
                        } else if (enemyboomr3 < 40) {
                            g.setColor(Color.YELLOW);
                        }
                        ;
                        //if (boomr==0&&tank.x<enemytank.x) {boomdirection=1;} else if (boomr==0&&tank.x>enemytank.x) {boomdirection=-1;};
                        if (plane.f == 1) {
                            g.fillOval((int) (planeboomdirection - enemyboomr3), (int) (600 - enemyboomr3), 2 * (int) (enemyboomr3), 2 * (int) (enemyboomr3));
                        } else {
                            g.fillOval((int) (planeboomdirection - enemyboomr3), (int) (600 - enemyboomr3), 2 * (int) (enemyboomr3), 2 * (int) (enemyboomr3));
                        }
                        if (enemyboomr3 < 39) {
                            enemyboomr3 = (enemyboomr3 + deltatime / 30.0);
                        } else {
                            enemyboomr3 = 0;
                            planeboom = 0;
                            try {
                                plane.bomb.initialize((plane.x + (plane.f + 1) * 60), plane.y, 30);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ;
                            plane.xstroke = -100000;
                            ;
                        }
                        ;
                    }
                    ;
                }
                plane.drawhealth(g);
                plane.update(deltatime / 70.0, 1);
            }
            if (plane.xstroke >= tank.x && plane.xstroke <= (tank.x + 120)) {
                tank.health -= 1;
                //System.out.println(tank.health);
            }
            ;
            if (ball.x >= plane.x && ball.x <= (plane.x + 120) && (800 - ball.y) >= (plane.y - 48) && (800 - ball.y) <= (plane.y)) {
                if (planestroke == 0) {
                    plane.health -= 3;
                    planestroke = 1;
                }
                //System.out.println(planestroke);
            } else {
                planestroke = 0; /*System.out.println(planestroke);*/
            }
            ;

            if (tank.health <= 0 && time < 100) {
                g.drawImage(dieImage, 0, 0, 1500, 800, null);
                time += deltatime / 10;
                if (time == 99) {
                    tank.health = 10;
                    plane.health = 15;
                    time = 0;
                }
            }
            ;
            if (plane.health <= 0 && enemytank.health <= 0 && timewin < 100) {
                g.drawImage(winImage, 0, 0, 1500, 800, null);
                timewin += deltatime / 10;
                if (Math.abs(timewin-100) <= 2) {
                    tank.health = 10;
                    enemytank.health = 2;
                    plane.health = 15;
                    timewin = 0;
                    numberOfTheLevel = 3;
                }
            }
            ;
            if (tank.health <= 0 && time < 100) {
                g.drawImage(dieImage, 0, 0, 1500, 800, null);
                time += deltatime / 10;
                if (time == 99) {
                    tank.health = 10;
                    plane.health = 15;
                    enemytank.health = 2;
                    time = 0;
                }
            }
            ;


        }


    }

    ;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (((e.getX() >= 50) && (e.getX() <= 160)) && ((e.getY() >= 15) && (e.getY() <= 55))) {
            if (tank.x < enemytank.x) {
                balldirection = -1;
            } else {
                balldirection = 1;
            }
            ;
            f1 = 1;
            f = -1;
            shootbuttonmoved=2;
            boomr = 0;
            new Thread(() -> {
                new TankFires().playSound("bip.wav");
            }).start();
            try {
                ball.initialize(ball.v, ball.angle, tank.x, (800 - tank.y));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            ;

        }
        ;
        if (numberOfTheLevel == 0) {
            if (((e.getX() >= 50) && (e.getX() <= 237)) && ((e.getY() >= 150) && (e.getY() <= 190))) {
                numberOfTheLevel = 1;
                //timelevel1 = 120;                                                      //на реальном запуске время увеличить до 200!!!!!!
                timelevel1=200;                                                                                                       //increase to 800!!!!
            }
        }
        ;
        if (numberOfTheLevel == -1) {
            if (((e.getX() >= 50) && (e.getX() <= 322)) && ((e.getY() >= 500) && (e.getY() <= 560))) {
                numberOfTheLevel = 0;
                timestudy = 120;                 //на реальном запуске время увеличить до 200!!!!!!
            }
            ;

        }
        if (numberOfTheLevel == 1&&timelevel1<0) {
            if ((e.getX() >= 50) && (e.getX() <= 228) && (e.getY() >= 140) && (e.getY() <= 180)) {
                timelevel2 = 800;
            }
            ;
        }
        ;
        if (numberOfTheLevel==2){
            if ((e.getX() >= 50) && (e.getX() <= 230) && (e.getY() >= 140) && (e.getY() <= 180)) {
                //plane.health=-1;
                timelevel3=800;
            }
        }
        if (((e.getX() >= 50) && (e.getX() <= 230)) && ((e.getY() >= 58) && (e.getY() <= 98))) {
            if (tank.x < enemytank.x) {
                balldirection = -1;
            } else {
                balldirection = 1;
            }
            ;
            try {
                ball.initialize(ball.v, ball.angle, tank.x, (800 - tank.y));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            ;
            timetraektory = 400;
            f = 1;

        }
        ;


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
       /* if (((e.getX()>=50)&&(e.getX()<=180))&&((e.getY()>=50)&&(e.getY()<=70))) {
            if (tank.x<enemytank.x){
                balldirection=-1;
            } else{balldirection=1;};
            try {
                ball.initialize(ball.v, ball.angle, tank.x, (800-tank.y));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            };
            timetraektory=100;

            f = 1;
        };*/
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (((e.getX() >= 50) && (e.getX() <= 160)) && ((e.getY() >= 15) && (e.getY() <= 55))) {
            shootbuttonmoved=1;
        } else {shootbuttonmoved=0;}
        if (((e.getX() >= 50) && (e.getX() <= 230)) && ((e.getY() >= 58) && (e.getY() <= 98))) {
            showtraektorybuttonmoved=1;
        } else {showtraektorybuttonmoved=0;}
        if (numberOfTheLevel ==-1){
            if (((e.getX() >= 50) && (e.getX() <= 322)) && ((e.getY() >= 500) && (e.getY() <= 560))) {
                studyf=1;
            } else {studyf=0;}
        }
        if (numberOfTheLevel==0){
            if (((e.getX() >= 50) && (e.getX() <= 237)) && ((e.getY() >= 150) && (e.getY() <= 190))){
                finishstudyf=1;
            } else {finishstudyf=0;}
        }
        if (numberOfTheLevel==1){
            if (((e.getX() >= 50) && (e.getX() <= 228)) && ((e.getY() >= 140) && (e.getY() <= 180))){
                goto2level=1;
            } else {goto2level=0;}
        }
        if (numberOfTheLevel==2){
            if (((e.getX() >= 50) && (e.getX() <= 230)) && ((e.getY() >= 140) && (e.getY() <= 180))){
                goto3level=1;
            } else {goto3level=0;}
        }

    }

    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    moveright = 1;
                    tankgo=1;
                    if (tankgof==0){
                    new Thread(() -> {
                        sound1.playSound("src/neiz_esten-z_uk-tanka.wav");
                    }).start();}
                    tankgof=1;
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    moveleft = 1;
                    tankgo=1;
                    if (tankgof==0){
                        new Thread(() -> {
                            sound1.playSound("src/neiz_esten-z_uk-tanka.wav");
                        }).start();}
                    tankgof=1;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    vplus = 1;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    vminus = 1;
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    angleplus = 1;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    angleminus = 1;
                }
                ;
            }
            ;
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    moveright = 0;
                    tankgo=0;
                    tankgof=0;
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    moveleft = 0;
                    tankgo=0;
                    tankgof=0;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    vplus = 0;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    vminus = 0;
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    angleplus = 0;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    angleminus = 0;
                }
                ;
            }
            ;


            return false;
        }
    }
}
