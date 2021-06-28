import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Raquete extends Rectangle {

    int id; // 1 ou 2
	int yVelocity;
	int speed = 10;

	//guarda a ultima tecla pressionada
	//serve para operar a logica que lida com os inputs
	//como o key event comeca a apitar um MONTE de keypress dps que vc segura por um determinado tempo
	//esse last key pressed serve pra nao ler a mesma tecla 500 vez
	int lastKeyPressed = 0;
	
	Raquete(int x, int y, int largura, int altura, int id){
		super(x, y, largura, altura);
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e) {
		
		switch(id) {
		case 1:
			if(e.getKeyCode() == lastKeyPressed){
				break;
			}

			lastKeyPressed = e.getKeyCode();

			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
			}
			break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == lastKeyPressed){
			lastKeyPressed = 0;
		}
		else{
			return;
		}

		if(e.getKeyCode()==KeyEvent.VK_W) {
			setYDirection(0);
			System.out.println("soltou W");
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			setYDirection(0);
			System.out.println("soltou S");
		}	
	}

	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		y = y + yVelocity;
	}
	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

	// checa se dois retangulos colidem
	public boolean checkBodyCollision(int rx, int ry, int rw, int rh)
	{
		//nao colidem pq estao um do lado do outro
		if((this.x + this.width) < rx || this.x > (rx + rw)){
			return false;
		}

		//nao colidem pq estao um em cima do outro
		if((this.y + this.height) < ry || this.y > (ry + rh)){
			return false;
		}

		return true;	
	}

	// checa se AS BORDAS DE dois retangulos colidem
	public boolean checkBorderCollision(int rx, int ry, int rw, int rh)
	{
		//nao colidem pq estao um do lado do outro
		if((this.x + this.width) < rx || this.x > (rx + rw)){
			return false;
		}

		//nao colidem pq estao um em cima do outro
		if((this.y + this.height) < ry || this.y > (ry + rh)){
			return false;
		}

		//nao colidem pq um ta dentro do outro
		if(this.x < rx && (this.x + this.width) > (rx + rw) && this.y < ry && (this.y + this.height) > (ry + rh)){
			return false;
		}

		//nao colidem pq um ta dentro do outro
		if(this.x > rx && (this.x + this.width) < (rx + rw) && this.y > ry && (this.y + this.height) < (ry + rh)){
			return false;
		}

		return true;	
	}
}