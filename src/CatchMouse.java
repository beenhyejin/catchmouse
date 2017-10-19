
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class CatchMouse extends JFrame {

	JFrame frame;
	private final int WIDTH = 570;//frame의 가로길이 
	private final int HEIGHT = 570;//frame의 세로길이
	DrawPanel drawPanel;
	private int bcX;	//이미지 x위치값
	private int bcY;	//이미지 y위치값
	int k = 0;			//이미지 선택을 위한 랜덤값
	PosImageIcon image;
	JLayeredPane lp = new JLayeredPane();//카드레이아웃
	Timer timer;
	public int delay = 800;//이미지 속도, 1000=1초
	PosImageIcon background;
	

	//----------------------------- main 함수 -----------------------------
	public static void main(String[] args){
		CatchMouse gui = new CatchMouse();
		gui.go();	
	}
	public void go(){
		//---------------------frame size 설정-----------------------------------
		frame = new JFrame("CatchMouse_Game");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//--------------------- panel 설정 -----------------------------------
		
		//----------------------frame에 panel추가 -----------------------------------------------
		
		drawPanel = new DrawPanel();
		//lp.add(drawPanel, new Integer(0));
		//frame.add(lp);
		frame.add(drawPanel);
		frame.setVisible(true);
		//-------------------Timer 설정---------------------------
		timer = new Timer(delay, new TimerListener());
		timer.start();
	}
	
	public class DrawPanel extends JPanel{
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g; //Graphics2D : Graphics 클래스를 확장해, 지오메트리, 좌표변화, 컬러 관리 및 텍스트 배치에 대해 고도의 제어를 실시
			background = new PosImageIcon("src/img/background.png",0,0,this.getWidth(),this.getHeight());
			background.draw(g);
			//------------------------고양이,쥐 아이콘 출력-------------------------------------
			bcX = (int)(Math.random()*450)+1;
			bcY = (int)(Math.random()*400)+1;
			k = (int)(Math.random()*22)+1;
			if(0<k&&k<=6){
				image = new PosImageIcon("src/img/cat2.png",bcX,bcY,70,70);
			}
			else if(7<=k&&k<=12){
				image = new PosImageIcon("src/img/cat3.PNG",bcX,bcY,60,60);
			}
			else if(13<=k&&k<=17){
				image = new PosImageIcon("src/img/mouse1.png",bcX,bcY,55,55);
			}
			else if(18<=k&&k<=22){
				image = new PosImageIcon("src/img/mouse2.PNG",bcX,bcY,50,50);
			}
			image.draw(g2d);
		}
	}
	//timer에 따라 실행되는 행위
	public class TimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPanel.repaint();
			
		}
		
	}
	
	
}
