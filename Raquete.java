import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Raquete extends Rectangle {

    int id; // 1 ou 2
	int yVelocity;
	int speed = 10;
	
	Raquete(int x, int y, int largura, int altura, int id){
		super(x, y, largura, altura);
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e) {
		
		switch(id) {
			case 1:
				if(e.getKeyCode()==KeyEvent.VK_W) {
					setYDirection(-speed);
				}
				if(e.getKeyCode()==KeyEvent.VK_S) {
					setYDirection(speed);
				}
				break;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) {
			setYDirection(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			setYDirection(0);
		}
		
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		y= y + yVelocity;
	}
	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

}