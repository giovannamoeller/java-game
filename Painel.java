import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Painel extends JPanel implements Runnable {
	static int width = 800;
	static int height = 600;
	static Dimension SCREEN_SIZE = new Dimension(width, height);

	static int bolaDiametro = 20;
	static int raqueteLargura = 25;
	static int raqueteAltura = 100;

	static int larguraRaquete = 10;
	static int alturaRaquete = 90;

	static int xRaqueteP1 = 5;
	static int xRaqueteP2 = 795 - larguraRaquete;
	static int yRaqueteP1 = 255;
	static int yRaqueteP2 = 255;

	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Raquete raqueteP1;
	Raquete raqueteP2;

	Painel(){
		novaRaquete();
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void novaRaquete() {
		raqueteP1 = new Raquete(xRaqueteP1, yRaqueteP1, larguraRaquete, alturaRaquete, 1);
		raqueteP2 = new Raquete(xRaqueteP2, yRaqueteP2, larguraRaquete, alturaRaquete, 2);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		raqueteP1.draw(g);
		raqueteP2.draw(g);
    	Toolkit.getDefaultToolkit().sync(); // I forgot to add this line of code in the video, it helps with the animation
	}

	public void move() {
		raqueteP1.move();
		raqueteP2.move();
	}
	
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {

			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				checkCollision();
				move();
				repaint();
				delta--;
			}
		}
	}

	public void checkCollision(){
		//raquete 1
		if(raqueteP1.checkBorderCollision(0, 0, this.width, this.height)){
			raqueteP1.yVelocity = - raqueteP1.yVelocity;
			raqueteP1.move();
			raqueteP1.yVelocity = 0;
		}

		//raquete 2
		if(raqueteP2.checkBorderCollision(0, 0, this.width, this.height)){
			raqueteP2.yVelocity = - raqueteP2.yVelocity;
			raqueteP2.move();
			raqueteP2.yVelocity = 0;
		}
	}

	// configuracao das teclas
	public static class keys
	{ 
		public static int P1UP = KeyEvent.VK_W;
		public static int P1DOWN = KeyEvent.VK_S;
		public static int P2UP = KeyEvent.VK_I;
		public static int P2DOWN = KeyEvent.VK_K;
	}

	// lida com teclas
	public class AL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e) {
			System.out.println("pressed key");
			// p1
			if(e.getKeyCode() == keys.P1UP) {
				raqueteP1.press(1);
			}
			if(e.getKeyCode() == keys.P1DOWN) {
				raqueteP1.press(-1);
			}
			
			// p2
			if(e.getKeyCode() == keys.P2UP) {
				raqueteP2.press(1);
			}
			if(e.getKeyCode() == keys.P2DOWN) {
				raqueteP2.press(-1);
			}
		}

		public void keyReleased(KeyEvent e) {
			System.out.println("released key " + e.getKeyCode());
			// p1
			if(e.getKeyCode() == keys.P1UP) {
				raqueteP1.release(1);
			}
			if(e.getKeyCode() == keys.P1DOWN) {
				raqueteP1.release(-1);
			}
			
			// p2
			if(e.getKeyCode() == keys.P2UP) {
				raqueteP2.release(1);
			}
			if(e.getKeyCode() == keys.P2DOWN) {
				raqueteP2.release(-1);
			}
		}
	}
}