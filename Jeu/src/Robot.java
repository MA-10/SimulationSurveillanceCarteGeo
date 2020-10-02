
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Robot extends Point {
	int freq;
    char direction;
    
    private ImageIcon imageIcon;
    
    public Robot(int x,int y,int freq)
          {
       		super(x,y);
    		this.freq=freq;
    		imageIcon = new ImageIcon(getClass().getResource("robot.png"));
          }
    
    public void draw(Graphics g) { 
			g.drawImage(imageIcon.getImage(),x*40, y*40, 40, 40, null);
			return;
		
	}

    public void move()
    {
  	  switch(direction) {
  	  case 'D': x++;
  	            break;
  	  case 'G': x--;
                  break;
  	  case 'H': y--;
        break;
  	  case 'B': y++;
        break;         
  	  }  
    }
    
    
    public void TournerD()
    {
  	  direction='D';      
    }
    
    
    public void TournerG()
    {
  	  direction='G';      
    }
    
    
    public void TournerH()
    {
  	  direction='H';      
    }
    
    
    public void TournerB()
    {
  	  direction='B';      
    }
    
    
    

}
