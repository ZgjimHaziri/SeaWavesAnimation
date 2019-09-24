import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel implements Runnable
{
	double[][] x = new double[8][2];
	double[][] y = new double[8][2];
	double[][] scale = new double[8][2];
	boolean[][] changeColor = new boolean[8][2];
	boolean[][] changeWave = new boolean[8][2];
    int[][] count = new int[8][2];

	int splash_x = -50;
	int splash_y = 370;
	boolean decrease_splash = false;
	boolean increase_splash = false;

	public void run() 
	{
		try
		{
			AudioClip clip = Applet.newAudioClip(new URL("file:WaveSound.wav"));
			clip.play();
			clip.loop();

			while(true)
			{
				Thread.sleep(75);
				moveWaves(0,-1.4, 1, 0.006,50,210,0.1,240);
				moveWaves(1, 3, 1, 0.006, 150,210,0.2,240);
				moveWaves(2, -4, 1, 0.006, 400, 205, 0.2, 245);
				moveWaves(3, -0.5, 1, 0.006, 500,215,0.2,245);
				moveWaves(4, 2.5, 1, 0.006, 600,210,0.2,240);
				moveWaves(5, -4, 1, 0.008,200,270,0.3,330);
				moveWaves(6, -1, 1, 0.008, 330, 270, 0.4, 330);
				moveWaves(7, 2.5, 1, 0.008, 470,270, 0.3, 330);

				if(y[6][0] >= 330 || y[6][1] >= 330)
				{
					increase_splash = true;
				}

				if (decrease_splash)
				{
					splash_y += 2;
				}
				else if (increase_splash)
				{
					splash_y -= 2;
				}

				if(splash_y<=335)
				{
					decrease_splash = true;
					increase_splash = false;
				}
				if(splash_y>=360)
				{
					decrease_splash = false;
				}

				repaint();
				
			}
		}
		
		catch(Exception e)
		{
			System.out.println("error");
		}
	}
	
	public Animation()
	{
		JFrame frame = new JFrame();
		frame.setSize(820,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.getContentPane().add(this);
		
		setValues(0,-1.4, 1, 0.006,50,210,0.1,240);
		setValues(1, 3, 1, 0.006, 150,210,0.2,240);
		setValues(2, -4, 1, 0.006, 400, 205, 0.2, 245);
		setValues(3, -0.5, 1, 0.006, 500,215,0.2,245);
		setValues(4, 2.5, 1, 0.006, 600,210,0.2,240);
		setValues(5, -4, 1, 0.008,200,270,0.3,330);
		setValues(6, -1, 1, 0.008, 330, 270, 0.4, 330);
		setValues(7, 2.5, 1, 0.008, 470,270, 0.3, 330);
	}
	
	public void setValues(int j,double x_scale, double y_scale, double sc, double startX, double startY, double startScale, double endY)
	{
		y[j][0] = startY;
		x[j][0] = startX;
		scale[j][0] = startScale;

		double y0 = (endY - startY)/2;
		while(startY<endY-y0)
		{
			startX+=x_scale;
			startY+=y_scale;
			startScale+=sc;
		}
		
		y[j][1] = startY;
		x[j][1] = startX;
		scale[j][1] = startScale;
		
		
	}

	public void moveWaves(int j, double x_scale, double y_scale, double sc, double startX, double startY, double startScale, double endY )
	{
		for(int i=0; i<2; i++)
		{
			x[j][i]+=x_scale;
			y[j][i]+=y_scale;
			scale[j][i]+=sc;
			
			if(y[j][i]==4008)
			{
				x[j][i]=startX; y[j][i]=startY; scale[j][i]=startScale;
			}

			if(y[j][i]==endY-4 && j<=4)changeColor[j][i]=true;
			else if(y[j][i]==endY)
			{
				y[j][i]=4000;
	
			}
			else if(y[j][i]==startY+4)changeColor[j][i]=false;

			if(count[j][i]++>=4)
				{
				 changeWave[j][i] = !changeWave[j][i];
				 count[j][i]=0;
				}
		}
	}

	public void wave(double x, double y, Graphics2D g, double i, boolean changeColor, boolean different_wave)
	{
		GeneralPath gp = new GeneralPath();
		g.setColor(Color.white);
		if(changeColor)g.setColor(new Color(113,177,187));

		double z = i*3;

		if(different_wave)
		{
			gp.moveTo(x, y);
			gp.quadTo(x + 18 * i, y - 10 * i - 4, x + 25 * i, y - 3 * i);
			gp.lineTo(x + 40 * i, y - 8 * i + 2);
			gp.quadTo(x + 48 * i, y + 4 * i -2, x + 65 * i, y - 6 * i + 1);
			gp.quadTo(x + 70 * i, y, x + 110 * i - 3, y - 8 * i + 4);
			gp.quadTo(x + 113 * i, y + 2 * i, x + 135 * i, y - 7 * i + 3);
			gp.lineTo(x + 154 * i, y - 4 * i + 1);
			gp.quadTo(x + 157 * i, y + 4 * i, x + 185 * i, y - 5 * i);
			gp.lineTo(x + 200 * i, y + 3 * i + 2 - 3);
			gp.quadTo(x + 225 * i, y - 7 * i, x + 250 * i, y + 3 * i);
			gp.lineTo(x + 264 * i, y - 3 * i + 2);
			gp.quadTo(x + 267 * i, y - 1 * i, x + 290 * i, y + 2 * i + 4);

			gp.moveTo(x, y);
			gp.quadTo(x + 13 * i, y + 3, x + 15 * i, y + 5 * i);
			gp.curveTo(x + 48 * i, y + 2 * i - z, x + 50 * i, y + 20 * i - z - 3, x + 60 * i, y + 10 * i - z);
			gp.quadTo(x + 75 * i, y + 17 * i - z - 4, x + 90 * i, y + 15 * i - z);
			gp.lineTo(x + 98 * i, y + 8 * i - z + 3);
			gp.lineTo(x + 110 * i, y + 15 * i - z );
			gp.quadTo(x + 130 * i, y + 8 * i - z, x + 145 * i, y + 15 * i - z);
			gp.curveTo(x + 182 * i, y + 5 * i - z, x + 190 * i, y + 22 * i - z - 3, x + 195 * i, y + 17 * i - z);
			gp.quadTo(x + 205 * i, y + 13 * i - z, x + 215 * i, y + 17 * i - z);
			gp.quadTo(x + 222 * i, y + 10 * i - z, x + 260 * i, y + 13 * i - z);
			gp.quadTo(x + 280 * i, y + 5 * i - z + 4, x + 290 * i, y + 2 * i + 4);
		}
		else
		{
			gp.moveTo(x, y);
			gp.quadTo(x + 18 * i, y - 10 * i, x + 25 * i, y - 3 * i);
			gp.lineTo(x + 40 * i, y - 8 * i);
			gp.quadTo(x + 48 * i, y + 4 * i, x + 65 * i, y - 6 * i);
			gp.quadTo(x + 70 * i, y, x + 110 * i, y - 8 * i);
			gp.quadTo(x + 113 * i, y + 2 * i, x + 135 * i, y - 7 * i);
			gp.lineTo(x + 154 * i, y - 4 * i);
			gp.quadTo(x + 157 * i, y + 4 * i, x + 185 * i, y - 5 * i);
			gp.lineTo(x + 200 * i, y + 3 * i);
			gp.quadTo(x + 225 * i, y - 7 * i, x + 250 * i, y + 3 * i);
			gp.lineTo(x + 264 * i, y - 3 * i);
			gp.quadTo(x + 267 * i, y - 1 * i, x + 290 * i, y + 2 * i);

			gp.moveTo(x, y);
			gp.quadTo(x + 13 * i, y, x + 15 * i, y + 5 * i);
			gp.curveTo(x + 48 * i, y + 2 * i - z, x + 50 * i, y + 20 * i - z, x + 60 * i, y + 10 * i - z);
			gp.quadTo(x + 75 * i, y + 17 * i - z, x + 90 * i, y + 15 * i - z);
			gp.lineTo(x + 98 * i, y + 8 * i - z);
			gp.lineTo(x + 110 * i, y + 15 * i - z);
			gp.quadTo(x + 130 * i, y + 8 * i - z, x + 145 * i, y + 15 * i - z);
			gp.curveTo(x + 182 * i, y + 5 * i - z, x + 190 * i, y + 22 * i - z, x + 195 * i, y + 17 * i - z);
			gp.quadTo(x + 205 * i, y + 13 * i - z, x + 215 * i, y + 17 * i - z);
			gp.quadTo(x + 222 * i, y + 10 * i - z, x + 260 * i, y + 13 * i - z);
			gp.quadTo(x + 280 * i, y + 5 * i - z, x + 290 * i, y + 2 * i);
		}

		g.fill(gp);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;

		Background obj = new Background();
		obj.paintBackground(g);

		wave(splash_x,splash_y,g2,3,false,true);

		for(int i=0; i<=7; i++)
		{
			wave(x[i][0],y[i][0],g2,scale[i][0],changeColor[i][0],changeWave[i][0]);
			wave(x[i][1],y[i][1],g2,scale[i][1],changeColor[i][1],changeWave[i][1]);
		}
		
        obj.paintSand(g2);
	}

	public static void main(String[] args) 
	{
		Animation an=new Animation();
		Thread t1=new Thread(an);
		t1.start();
	}
}