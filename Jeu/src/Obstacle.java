import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class Obstacle extends Point  {
	
	ImageIcon imageIcon;
	Obstacle(int x, int y)
	{
		super(x,y);
		imageIcon = new ImageIcon(getClass().getResource("obstacle.png"));
	}

	public void draw(Graphics g) {
		g.drawImage(imageIcon.getImage(), x*40, y*40, 40, 40, null);
		return;
		
	}

}
