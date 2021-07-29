import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// BOLA QUADRADAKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
public class Bola extends Rectangle {

    Vetor velocity;
	int speed = 10;
	
	Bola(Par coord, int raio)
    {
		super(coord.x, coord.y, raio, raio);
        velocity = new Vetor(0, 0);
	}

	public Par getCoords() { return new Par(this.x, this.y); }
	public Par getDim() { return new Par(this.width, this.height); }

	public void generateRandomVelocity(int spread)
	{
        spread %= 360;
        spread = (spread != 0) ? spread : 360;

		// gera um vetor que tem de 0 a 120 graus e gira ele pra apontar pra direcao do gol
		velocity.randomVector(spread);
		velocity.rotateVector(180 - (spread / 2));
		// espelha horizontalmente pra compensar a geracao a partir do QD 1 da circ. trigonometrica e habitar um espaco do QD 4 no jogo
		velocity.mirrorHor();
		// roda um numero aleatorio pra determinar se aponta pro P1 ou P2
		if(((int) (Math.random() * 99)) % 2 == 0){
			velocity.mirrorVer();
		}
	}

	public void setXDirection(int direction)
    {
		velocity.x = direction;
	}
	
	public void setYDirection(int direction)
    {
		velocity.y = direction;
	}

	public void bounceHor(int desvio)
	{
		velocity.mirrorHor();
		if(desvio != 0){
			velocity.rotateVector(((int) (Math.random() * desvio)) - (desvio * 2));
		}
	}
	public void bounceVer(int desvio)
	{
		velocity.mirrorVer();
		if(desvio != 0){
			velocity.rotateVector(((int) (Math.random() * desvio)) - (desvio * 2));
		}
	}	

	public void move()
	{
		x += (int) (velocity.x * speed);
		y -= (int) (velocity.y * speed);
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