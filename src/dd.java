
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;



class GraphicObject{
   BufferedImage img = null;
   int x = 0, y = 0;
   int width = 0, height = 0;
   
   public GraphicObject(String name){
      try {
         img = ImageIO.read(new File(name));
         width = img.getWidth();
         height = img.getHeight();
      } catch (IOException e) {
         System.out.println(e.getMessage());
         System.exit(0);
      }
   }
   public int getwidth(){
      return width;
   }
   public int getheight(){
      return height;
   }
   public void update(){ }
   public void draw(Graphics g){
      g.drawImage(img, x, y, null);
   }
   
   public void keyPressed(KeyEvent event){}
}

class Missile extends GraphicObject {
   boolean launched = false;
   public Missile(String name){
      super(name);
      y = -200;
   }
   public void update() {
      if(launched) y -= 10;
      if( y <-100) launched = false;
   }
   
   public int getX(){
      return x;
   }
   public int getY(){
      return y;
   }
   
   public void keyPressed(KeyEvent event, int x, int y){
      if( event.getKeyCode() == KeyEvent.VK_SPACE){
         launched = true;
         this.x = x;
         this.y = y;
      }
   }
}

class Enermy extends GraphicObject { // 적 캐릭터
   int dx = -10;
   int dy = 2;
   public Enermy(String name){
      super(name);
      x = 500;
      y = 0;
   }
   
   public void update() {
      x += dx;
      y += dy;
      if (x < 0) dx = +((int)(Math.random()*20));
      if (x > 500 - getwidth()) dx = -((int)(Math.random()*20));
      if (y < 0) dy = +((int)(Math.random()*20));
      if (y > 100) dy = -((int)(Math.random()*20));
   }
   public int getX(){
      return x;
   }
   public int getY(){
      return y;
   }
}

class SpaceShip extends GraphicObject {
   public SpaceShip(String name){
      super(name);
      x = 150;
      y = 350;
   }
   
   public void keyPressed(KeyEvent event){
      if (event.getKeyCode() == KeyEvent.VK_LEFT){ x-= 10; }
      if (event.getKeyCode() == KeyEvent.VK_RIGHT){ x+= 10; }
      if (event.getKeyCode() == KeyEvent.VK_UP){ y -= 10; }
      if (event.getKeyCode() == KeyEvent.VK_DOWN){ y += 10; }
   }
}

class MyPanel extends JPanel implements KeyListener {
   Enermy enermy;
   SpaceShip spaceship;
   Missile missile;
   boolean drawok;
   public MyPanel() {
      super();
      this.addKeyListener(this);
      this.requestFocus();
      setFocusable(true);
      drawok = true;
      //enermy = new Enermy("C:\\Users\\ds\\workspace\\Lab23_B089063\\src\\enermy.png");
      enermy = new Enermy("C:\\dev\\springworkspace\\test\\src\\test\\enermy.png");
      //spaceship = new SpaceShip("C:\\Users\\ds\\workspace\\Lab23_B089063\\src\\spaceship.png");
      spaceship = new SpaceShip("C:\\dev\\springworkspace\\test\\src\\test\\spaceship.png");
      //missile = new Missile("C:\\Users\\ds\\workspace\\Lab23_B089063\\src\\missile.png");
      missile = new Missile("C:\\dev\\springworkspace\\test\\src\\test\\missile.png");
      
      class MyThread extends Thread {
         public void run(){
            while(true){
               if(((enermy.x-enermy.getwidth()/2 < missile.x)&&(missile.x < enermy.x+enermy.getwidth()/2))&&
                     ((enermy.y-enermy.getheight()/2 < missile.y)&&(missile.y < enermy.y+enermy.getheight()/2))){
                  drawok = false;
               }
               if(drawok == true){
                  enermy.update();
               }
               spaceship.update();
               missile.update();
               repaint();
               try{ Thread.sleep(50); }
               catch(InterruptedException e){}
            }
         }
      }
      Thread t = new MyThread();
      t.start();
   }
   @Override
   public void paint(Graphics g){
      super.paint(g);
      if(drawok == true){
         enermy.draw(g);
      }
      spaceship.draw(g);
      missile.draw(g);
   }
   
   public void keyPressed(KeyEvent event){
      spaceship.keyPressed(event);
      missile.keyPressed(event, spaceship.x, spaceship.y);
   }
   
   public void keyReleased(KeyEvent arg0){ }
   public void keyTyped(KeyEvent arg0){ }
}


public class MyFrame extends JFrame{
   public MyFrame() {
      setTitle("My Game");
      add(new MyPanel());
      setSize(500, 500);
      setVisible(true);
   }
   
   public static void main(String[] args){
      new MyFrame();
   }
}
