
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
	private final int WIDTH = 570;//frame�� ���α��� 
	private final int HEIGHT = 570;//frame�� ���α���
	DrawPanel drawPanel;
	private int bcX;	//�̹��� x��ġ��
	private int bcY;	//�̹��� y��ġ��
	int k = 0;			//�̹��� ������ ���� ������
	PosImageIcon image;
	JLayeredPane lp = new JLayeredPane();//ī�巹�̾ƿ�
	Timer timer;
	public int delay = 800;//�̹��� �ӵ�, 1000=1��
	PosImageIcon background;
	

	//----------------------------- main �Լ� -----------------------------
	public static void main(String[] args){
		CatchMouse gui = new CatchMouse();
		gui.go();	
	}
	public void go(){
		//---------------------frame size ����-----------------------------------
		frame = new JFrame("CatchMouse_Game");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//--------------------- panel ���� -----------------------------------
		
		//----------------------frame�� panel�߰� -----------------------------------------------
		
		drawPanel = new DrawPanel();
		//lp.add(drawPanel, new Integer(0));
		//frame.add(lp);
		frame.add(drawPanel);
		frame.setVisible(true);
		//-------------------Timer ����---------------------------
		timer = new Timer(delay, new TimerListener());
		timer.start();
	}
	
	public class DrawPanel extends JPanel{
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g; //Graphics2D : Graphics Ŭ������ Ȯ����, ������Ʈ��, ��ǥ��ȭ, �÷� ���� �� �ؽ�Ʈ ��ġ�� ���� ���� ��� �ǽ�
			background = new PosImageIcon("src/img/background.png",0,0,this.getWidth(),this.getHeight());
			background.draw(g);
			//------------------------�����,�� ������ ���-------------------------------------
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
	//timer�� ���� ����Ǵ� ����
	public class TimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPanel.repaint();
			
		}
		
	}
	
	
}
