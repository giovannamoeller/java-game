import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class UIRect extends Rectangle
{
    Color color;

    UIRect(int x, int y, int w, int h, Color color)
    {
        this.x = x;        
        this.y = y;        
        this.width = w;        
        this.height = h;            
        this.color = color;
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
}