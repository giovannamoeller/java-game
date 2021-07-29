import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class UINumber extends Rectangle
{
    UIRect rect[];
    Color primary;
    int stroke;

    static boolean numbers[][] = {
        //    0      1      2      3      4      5      6
        {  true,  true,  true, false,  true,  true,  true }, // 0      
        { false, false,  true, false, false,  true, false }, // 1      
        {  true, false,  true,  true,  true, false,  true }, // 2      
        {  true, false,  true,  true, false,  true,  true }, // 3      
        { false,  true,  true,  true, false,  true, false }, // 4      
        {  true,  true, false,  true, false,  true,  true }, // 5      
        {  true,  true, false,  true,  true,  true,  true }, // 6      
        {  true, false,  true, false, false,  true, false }, // 7       
        {  true,  true,  true,  true,  true,  true,  true }, // 8      
        {  true,  true,  true,  true, false,  true,  true }  // 9         
    };

    UINumber(int x, int y, int w, int h, int stroke, Color primary)
    {
        this.x = x;        
        this.y = y;        
        this.width = w;        
        this.height = h;        
        this.stroke = stroke;    
        this.primary = primary;
        
        initRects();
    }

    void initRects()
    {
        rect = new UIRect[7];
        rect[0] = new UIRect(stroke                  , 0                    , width - (2 * stroke) , stroke, primary);    
        rect[1] = new UIRect(0                       , 0                    , stroke               , (height + stroke) / 2, primary);    
        rect[2] = new UIRect(width - stroke          , 0, stroke            , (height + stroke) / 2, primary);
        rect[3] = new UIRect(stroke                  , (height - stroke) / 2, width - (2 * stroke) , stroke, primary);
        rect[4] = new UIRect(0, (height - stroke) / 2, stroke               , (height + stroke) / 2, primary);
        rect[5] = new UIRect(width - stroke          , (height - stroke) / 2, stroke               , (height + stroke) / 2, primary);
        rect[6] = new UIRect(stroke                  , height - stroke      , width - (2 * stroke) , stroke, primary);
    }

    public void draw(Graphics g, int num)
	{
        num %= 10;

		try{
			g.setColor(primary);
		}
		catch(Exception e){
			g.setColor(Color.white);
		}

        for(int i = 0; i != 7; i++){
            if(numbers[num][i]){
                g.fillRect(this.x + rect[i].x, this.y + rect[i].y, rect[i].width, rect[i].height);
            }
        }
	}
}