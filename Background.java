import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Background extends JPanel
{
	Background obj ;
	
	Image clouds, sand;
	ImageIcon i1,i2;
	
	
	int c_width, c_height;
	int s_width, s_height;

	
	public Background()
	{	
		 i1=new ImageIcon("clouds.png");
	     i2=new ImageIcon("sand.png");
	     clouds = i1.getImage();
	     sand = i2.getImage();
	     c_width = i1.getIconWidth();
	     c_height = i1.getIconHeight();
	     s_width = i2.getIconWidth();
	     s_height = i2.getIconHeight();
	}
	
	public void paintBackground(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(clouds, 0, 0, this);
		
		
		GradientPaint gp = new GradientPaint(0,c_height, new Color(11,81,123), 0,460-s_height-20, new Color(30,187,161));
		
		g2.setPaint(gp);
		
		g2.fillRect(0, c_height, 820, 460-s_height-c_height);
	}
	
	public void paintSand(Graphics2D g2)
	{
		g2.drawImage(sand, 0, 460-s_height, this);

	}
}
