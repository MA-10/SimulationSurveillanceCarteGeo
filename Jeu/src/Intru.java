import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Intru extends Point  {
	int freq;
    char direction;
    boolean active;
    Vector<Sac> sacs ;
    private ImageIcon imageIcon1;
    private ImageIcon imageIcon2;
   
    
    public Intru(int x,int y,int freq )
          {super(x,y);
          this.freq=freq;
          this.active=true;
          sacs=new Vector<Sac>(2);
          imageIcon1 = new ImageIcon(getClass().getResource("intrus.png"));
          imageIcon2 = new ImageIcon(getClass().getResource("intrussac.png"));
          }
   
          
    public void draw(Graphics g) { 

		if (active) { 
			if (sacs.size()==0) {
				g.drawImage(imageIcon1.getImage(), x*40, y*40, 40, 40, null);
			}
			else
				g.drawImage(imageIcon2.getImage(), x*40, y*40, 40, 40, null);
			
			return;
		}
		
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
    
    
   
    public boolean PrendSac(Sac sac)
    {
    	if (sacs.size() < 2)
    	{
    		sacs.add(sac);
    		return true;
    	}
    	return false;
    }
    
    public void retournerSac() {
    	sacs.removeAllElements();
    }
    	
    
    
}
