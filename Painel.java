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

	static int xRaquete = 5;
	static int xRaqueteOponente = 795 - larguraRaquete;
	static int yRaquete = 255;
	static int yRaqueteOponente = 255;

	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Raquete raquete;
	Raquete raqueteOponente;
	
	Painel(){
		novaRaquete();
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void novaRaquete() {
		raquete = new Raquete(xRaquete, yRaquete, larguraRaquete, alturaRaquete, 1);
		raqueteOponente = new Raquete(xRaqueteOponente, yRaqueteOponente, larguraRaquete, alturaRaquete, 2);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		raquete.draw(g);
		raqueteOponente.draw(g);
    	Toolkit.getDefaultToolkit().sync(); // I forgot to add this line of code in the video, it helps with the animation
	}

	public void move() {
		raquete.move();
		raqueteOponente.move();
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
		if(raquete.checkBorderCollision(0, 0, this.width, this.height)){
			raquete.yVelocity = - raquete.yVelocity;
			raquete.move();
			raquete.yVelocity = 0;
		}

		//raquete 2
	}

	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			raquete.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			raquete.keyReleased(e);
		}
	}
}