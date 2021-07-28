import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Raquete extends Rectangle
{
	int yVelocity;
	int speed = 10;
	Color color;


	//guarda a ultima tecla pressionada
	//serve para operar a logica que lida com os inputs
	//como o key event comeca a apitar um MONTE de keypress dps que vc segura por um determinado tempo
	//esse last key pressed serve pra nao ler a mesma tecla 500 vez
	int lastKeyPressed = 0;
	
	Raquete(Par coord, Par dim, Color color)
	{
		super(coord.x, coord.y, dim.x, dim.y);
		this.color = color;
	}
	
	public void press(int key)
	{	
		if(key == lastKeyPressed){
			return;
		}

		lastKeyPressed = key;

		if(key == 1) {
			setYDirection(-speed);
		}
		if(key == -1) {
			setYDirection(speed);
		}
	}

	public void release(int key)
	{
		if(key == lastKeyPressed){
			lastKeyPressed = 0;
		}
		else{
			return;
		}

		if(key == 1) {
			setYDirection(0);
		}
		if(key == -1) {
			setYDirection(0);
		}	
	}

	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	public void move() {
		y += yVelocity;
	}

	public void draw(Graphics g)
	{
		try{
			g.setColor(color);
		}
		catch(Exception e){
			g.setColor(Color.white);
		}
		g.fillRect(x, y, width, height);
	}

	// checa se dois retangulos colidem
	public boolean checkBodyCollision(Par rc, Par rd)
	{
		//nao colidem pq estao um do lado do outro
		if((this.x + this.width) < rc.x || this.x > (rc.x + rd.x)){
			return false;
		}

		//nao colidem pq estao um em cima do outro
		if((this.y + this.height) < rc.y || this.y > (rc.y + rd.y)){
			return false;
		}

		return true;	
	}

	// checa se AS BORDAS DE dois retangulos colidem
	public boolean checkBorderCollision(Par rc, Par rd)
	{
		//nao colidem pq estao um do lado do outro
		if((this.x + this.width) < rc.x || this.x > (rc.x + rd.x)){
			return false;
		}

		//nao colidem pq estao um em cima do outro
		if((this.y + this.height) < rc.y || this.y > (rc.y + rd.y)){
			return false;
		}

		//nao colidem pq um ta dentro do outro
		if(this.x < rc.x && (this.x + this.width) > (rc.x + rd.x) && this.y < rc.y && (this.y + this.height) > (rc.y + rd.y)){
			return false;
		}

		//nao colidem pq um ta dentro do outro
		if(this.x > rc.x && (this.x + this.width) < (rc.x + rd.x) && this.y > rc.y && (this.y + this.height) < (rc.y + rd.y)){
			return false;
		}

		return true;	
	}
}