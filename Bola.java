import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// BOLA QUADRADAKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
public class Bola extends Rectangle {

    Par velocity;
	int speed = 5;
	
	Bola(Par coord, int raio)
    {
		super(coord.x, coord.y, raio, raio);
        velocity = new Par(0, 0);
	}

	public void setXDirection(int direction)
    {
		velocity.x = direction;
	}
	
	public void setYDirection(int direction)
    {
		velocity.y = direction;
	}

	public void move() {
		x += velocity.x;
		y += velocity.y;
	}

	public void draw(Graphics g)
    {
        g.setColor(Color.white);
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