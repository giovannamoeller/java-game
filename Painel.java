import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Painel extends JPanel implements Runnable
{
	static Par dim = new Par(800, 600);
	static Dimension SCREEN_SIZE = new Dimension(dim.x, dim.y);

	int bolaNoColissionFrames = 0;
	static int bolaRaio = 10;
	static int bolaSpread = 90;
	static Par coordBola = new Par((dim.x / 2) - bolaRaio, (dim.y / 2) - bolaRaio);

	static Par dimRaquete = new Par(10, 90);
	static int paddingRaquete = 5;
	static Par coordP1 = new Par(paddingRaquete, (dim.y - dimRaquete.y) / 2);
	static Par coordP2 = new Par(dim.x - (paddingRaquete + dimRaquete.x), (dim.y - dimRaquete.y) / 2);

	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Bola bola;
	Raquete raqueteP1, raqueteP2;

	Painel()
	{
		novaRaquete();
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);

		bola = new Bola(coordBola, bolaRaio);
		initBola();
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void novaRaquete()
	{
		raqueteP1 = new Raquete(coordP1, dimRaquete, Color.blue);
		raqueteP2 = new Raquete(coordP2, dimRaquete, Color.red);
	}

	public void initBola()
	{
		bola.generateRandomVelocity(bolaSpread);
		bola.x = coordBola.x;
		bola.y = coordBola.y;
	}

	public void paint(Graphics g)
	{
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g)
	{
		raqueteP1.draw(g);
		raqueteP2.draw(g);
		bola.draw(g);
    	Toolkit.getDefaultToolkit().sync();
	}

	public void move()
	{
		raqueteP1.move();
		raqueteP2.move();
		bola.move();
	}
	
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				checkCollision();
				move();
				repaint();
				bolaNoColissionFrames -= (bolaNoColissionFrames == 0) ? 0 : 1;
				delta--;
			}
		}
	}

	public void checkCollision(){
		// P1 -> campo
		if(raqueteP1.checkBorderCollision(new Par(0, 0), dim)){
			raqueteP1.yVelocity *= -1;
			raqueteP1.move();
			raqueteP1.yVelocity = 0;
		}

		// P2-> campo
		if(raqueteP2.checkBorderCollision(new Par(0, 0), dim)){
			raqueteP2.yVelocity *= -1 ;
			raqueteP2.move();
			raqueteP2.yVelocity = 0;
		}

		// bola -> P1 e P2
		if(bolaNoColissionFrames == 0
			&& (bola.checkBodyCollision(raqueteP1.getCoords(), raqueteP1.getDim())
			|| bola.checkBodyCollision(raqueteP2.getCoords(), raqueteP2.getDim()))){
			bolaNoColissionFrames = 10;
			bola.bounceVer(10);
		}

		// bola -> campo
		if(bola.checkBodyCollision(new Par(0, 0), new Par(this.dim.x, 0))
			|| bola.checkBodyCollision(new Par(0, this.dim.y), new Par(this.dim.x, 0))){
			bola.bounceHor(0);
		}

		// bola -> gol P1
		if(bola.checkBodyCollision(new Par(0, 0), new Par(0, this.dim.y))){
			System.out.println("GOL ENOIS");
			initBola();
		}
		// bola -> gol P2
		if(bola.checkBodyCollision(new Par(this.dim.x, 0), new Par(0, this.dim.y))){
			System.out.println("GOL ENOIS");
			initBola();
		}
	}

	// configuracao das teclas
	public static class keys
	{ 
		public static int P1UP = KeyEvent.VK_W;
		public static int P1DOWN = KeyEvent.VK_S;
		public static int P2UP = KeyEvent.VK_O;
		public static int P2DOWN = KeyEvent.VK_K;
	}

	// lida com teclas
	public class AL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
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

		public void keyReleased(KeyEvent e)
		{
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