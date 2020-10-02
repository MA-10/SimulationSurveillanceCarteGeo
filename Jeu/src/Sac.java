
	
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Sac extends Point  {

	private ImageIcon imageIcon;
	Sac(int x,int y)
	{
		super(x,y);
		imageIcon = new ImageIcon(getClass().getResource("sac.png"));
	}
	 public void draw(Graphics g) { 
			g.drawImage(imageIcon.getImage(),x*40, y*40, 40, 40, null);
			return;	
	}
	

}
