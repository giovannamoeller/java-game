import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// BOLA QUADRADAKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
public class Bola extends Rectangle {

    int xVelocity;
	int yVelocity;
	int speed = 5;
	
	Bola(int x, int y, int raio)
    {
		super(x, y, raio, raio);
        
	}

	public void setXDirection(int xDirection)
    {
		xVelocity = xDirection;
	}
	
	public void setYDirection(int yDirection)
    {
		yVelocity = yDirection;
	}

	public void move() {
		x = x + xVelocity;
		y = y + yVelocity;
	}

	public void draw(Graphics g)
    {
        g.setColor(Color.white);
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