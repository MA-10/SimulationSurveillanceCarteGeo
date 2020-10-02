
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Sortie extends Point {
	ImageIcon imageIcon;
	Sortie(int x , int y)
	{
		super(x,y);
		imageIcon = new ImageIcon(getClass().getResource("exit.png"));
	}
	public void draw(Graphics g) { 
		g.drawImage(imageIcon.getImage(),x*40, y*40, 40, 40, null);
		return;	
}

}
